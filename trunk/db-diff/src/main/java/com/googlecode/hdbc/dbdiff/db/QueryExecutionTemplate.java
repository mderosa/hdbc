package com.googlecode.hdbc.dbdiff.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.DataSource;

import com.googlecode.hdbc.dbdiff.db.IQueryDefinition;
import com.googlecode.hdbc.dbdiff.model.DbObjects;

public class QueryExecutionTemplate<T> {

	public final DbObjects<T> executeQuery(final IQueryDefinition<T> definition,
	        final DataSource ods) throws SQLException {
		Connection cnn = null;
		PreparedStatement stmt = null;
		try {
		    cnn = ods.getConnection();
			stmt = cnn.prepareStatement(definition.getParameterizedSQL());
			definition.processStatement(stmt);
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
		    throw new IllegalStateException(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {	}
			}
			if (cnn != null) {
				try {
					cnn.close();
				} catch (SQLException e) {	}
			}
		}
		return definition.result();
	}
}
