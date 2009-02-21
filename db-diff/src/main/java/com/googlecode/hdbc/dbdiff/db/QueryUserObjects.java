package com.googlecode.hdbc.dbdiff.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.googlecode.hdbc.dbdiff.model.UserObjectDefinition;
import com.googlecode.hdbc.dbdiff.model.UserObjects;

public class QueryUserObjects implements IQueryDefinition<UserObjectDefinition> {
    private UserObjects result;
    private static final String SQL = "SELECT object_name, object_type FROM user_objects";

    public QueryUserObjects(final String owningDb) {
        result = new UserObjects(owningDb);
    }

    public final String getParameterizedSQL() {
       return SQL;

    }

    public final void processStatement(final PreparedStatement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String objectName = rs.getString("object_name");
            String objectType = rs.getString("object_type");
            result.add(new UserObjectDefinition(objectName, objectType));
        }
    }

    public final UserObjects result() {
        return result;
    }

}
