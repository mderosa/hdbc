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
	public int getCurrentVersion() throws SQLException {
		int version = 0;
		
		DataSource ds = this.makeDataSource(props);
		Connection cn = null;
		Statement stmt = null;
		try {
			cn = ds.getConnection();
			stmt = cn.createStatement();
			stmt.execute("SELECT version FROM version");
			ResultSet rs = stmt.getResultSet();
			if (rs.next()) {
				version = rs.getInt(1);
			} 
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
        OracleDataSource ods;
        try {
            ods = new OracleDataSource();
            ods.setConnectionCachingEnabled(true);
            String url = props.getProperty("db.jdbc.connection.string");
            ods.setURL(url);
            String user = props.getProperty("db.user");
            ods.setUser(user);
            String pwd = props.getProperty("db.password");
            ods.setPassword(pwd);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
        return ods;
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
