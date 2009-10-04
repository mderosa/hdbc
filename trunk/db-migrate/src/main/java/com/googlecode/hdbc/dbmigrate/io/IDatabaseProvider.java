package com.googlecode.hdbc.dbmigrate.io;

import java.sql.SQLException;

public interface IDatabaseProvider {

	int getCurrentVersion() throws SQLException;
}
