package com.googlecode.hdbc.dbmigrate.io;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;
import oracle.jdbc.pool.OracleDataSource;

public class DatabaseProvider implements IDatabaseProvider {
	private Properties props;
	public DatabaseProvider(Properties properties) {
		props = properties;
	}

	@Override
	public int getCurrentVersion() {
		int version = 0;
		
		DataSource ds = this.makeDataSource(props);
		Connection cn = null;
		Statement stmt = null;
		try {
			cn = ds.getConnection();
			stmt = cn.createStatement();
			stmt.execute("SELECT version FROM version");
			ResultSet rs = stmt.getResultSet();
			boolean found = rs.first();
			if (found) {
				version = rs.getInt(0);
			} 
				
		} catch (SQLException e) {
			
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {}
			}
			if (cn != null) {
				try {
					cn.close();
				} catch (SQLException e) {}
			}
		}
		this.closeDatasource(ds);
		return version;
	}
	
    protected final DataSource makeDataSource(final Properties props) {
        OracleDataSource odsTest;
        try {
            odsTest = new OracleDataSource();
            odsTest.setConnectionCachingEnabled(true);
            String url = props.getProperty("db.jdbc.connection.string");
            odsTest.setURL(url);
            String user = props.getProperty("db.user");
            odsTest.setUser(user);
            String pwd = props.getProperty("db.password");
            odsTest.setPassword(pwd);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return odsTest;
    }

    private void closeDatasource(final DataSource ds) {
        if (ds == null) {
            return;
        }

        try {
            ((OracleDataSource) ds).close();
        } catch (SQLException e) {}
    }

}
