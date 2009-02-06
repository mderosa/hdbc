package com.googlecode.hdbc.dbdiff.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.googlecode.hdbc.dbdiff.model.DbObjects;

public interface IQueryDefinition<T> {

	enum Parameter {
		ONE,
		TWO,
		THREE,
		FOUR,
		FIVE,
		SIX,
		SEVEN,
		EIGHT,
		NINE,
		TEN;
		public int index() {
			return ordinal() + 1;
		}
	}

	String getParameterizedSQL();

	void processStatement(PreparedStatement stmt)  throws SQLException;

	DbObjects<T> result();
}
