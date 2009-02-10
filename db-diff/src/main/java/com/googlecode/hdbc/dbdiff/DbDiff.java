package com.googlecode.hdbc.dbdiff;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import oracle.jdbc.pool.OracleDataSource;

import com.googlecode.hdbc.dbdiff.db.Database;
import com.googlecode.hdbc.dbdiff.db.IDatabase;
import com.googlecode.hdbc.dbdiff.model.Analyze;
import com.googlecode.hdbc.dbdiff.model.ColumnDefinition;
import com.googlecode.hdbc.dbdiff.model.TabularObjects;
import com.googlecode.hdbc.dbdiff.model.UserObjectDefinition;
import com.googlecode.hdbc.dbdiff.model.UserObjects;
import com.googlecode.hdbc.dbdiff.utilities.Pair;

public class DbDiff {

	public enum Check {
		ObjectsExist,
		ObjectDetail
	}

	public static final String TEST_CONNECTION_STRING = "test.db.jdbc.connection.string";
	public static final String TEST_USER = "test.db.user";
    public static final String TEST_PWD = "test.db.password";
    public static final String REF_CONNECTION_STRING = "ref.db.jdbc.connection.string";
    public static final String REF_USER = "ref.db.user";
    public static final String REF_PWD = "ref.db.password";

	public static void main(final String[] args) {
		DbDiff diff = new DbDiff();

		try {
    		Properties props = diff.readProperties();
    		Pair<DataSource, DataSource> testAndRef = diff.makeDataSources(props);

    		boolean dbsAreIdentical = diff.diffUserObjects(new Database("test", testAndRef.getLeft()),
    				new Database("ref", testAndRef.getRight()),
    				Check.ObjectDetail);

    		if (dbsAreIdentical) {
    			System.out.println("OK: the databases are identical");
    		} else {
    			System.out.println("WARNING: the databases are not identical");
    		}
		} catch (Exception e) {
		    System.out.println("FAIL: " + e.getMessage());
		}
	}

	protected final boolean diffUserObjects(final IDatabase testDb,
			final IDatabase refDb, final Check checkExtent) {
		UserObjects testObjects = testDb.selectUserObjects();
		UserObjects refObjects = refDb.selectUserObjects();
		Analyze<UserObjectDefinition> analyzed =
			new Analyze<UserObjectDefinition>("full database")
			.collections(testObjects.getConstituentObjects(),
					refObjects.getConstituentObjects());
		boolean dbsAreIdentical = analyzed.areEqual();

		if (checkExtent == Check.ObjectDetail) {
			for (UserObjectDefinition def : analyzed.getCommon()) {
				if (def.isTabularObject()) {
					if(!diffTabularObject(testDb, refDb, def.getObjectName())) {
						dbsAreIdentical = false;
					}
				}
			}
		}
		return dbsAreIdentical;
	}

	protected final boolean diffTabularObject(final IDatabase testDb,
			final IDatabase refDb, final String objectName) {
	    System.out.println(objectName);
		TabularObjects testObj = testDb.selectTabularObject(objectName);
		TabularObjects refObj = refDb.selectTabularObject(objectName);
		return new Analyze<ColumnDefinition>(objectName)
			.collections(testObj.getConstituentObjects(), refObj.getConstituentObjects())
			.areEqual();
	}

	protected final Properties readProperties() throws IOException {
	    File file = new File(".");
	    String path = file.getCanonicalPath();
	    FileInputStream steam = new FileInputStream(path + "/config.xml");
	    Properties props = new Properties();
	    props.loadFromXML(steam);
	    return props;
	}

	protected final Pair<DataSource, DataSource> makeDataSources(final Properties props) {
	    OracleDataSource odsTest;
	    OracleDataSource odsRef;
	    try {
    	    odsTest = new OracleDataSource();
    	    String url = props.getProperty(TEST_CONNECTION_STRING);
            odsTest.setURL(url);
            String user = props.getProperty(TEST_USER);
            odsTest.setUser(user);
            String pwd = props.getProperty(TEST_PWD);
            odsTest.setPassword(pwd);
    	    odsRef = new OracleDataSource();
    	    odsRef.setURL(props.getProperty(REF_CONNECTION_STRING));
    	    odsRef.setUser(props.getProperty(REF_USER));
    	    odsRef.setPassword(props.getProperty(REF_PWD));
	    } catch (SQLException e) {
	        throw new IllegalStateException(e);
	    }
	    return new Pair<DataSource, DataSource>(odsTest, odsRef);
	}
}

