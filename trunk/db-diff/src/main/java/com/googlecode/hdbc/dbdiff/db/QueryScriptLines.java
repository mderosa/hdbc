package com.googlecode.hdbc.dbdiff.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.googlecode.hdbc.dbdiff.model.DbObjects;
import com.googlecode.hdbc.dbdiff.model.ScriptedObjects;

public class QueryScriptLines implements IQueryDefinition<String> {
    private final String scriptedObjName;
    private final ScriptedObjects scriptObj;
    private static final String SQL = "SELECT text " +
        "FROM user_source " +
        "WHERE name = ? " +
        "AND (type = 'FUNCTION' or type = 'PROCEDURE') " +
        "ORDER BY line";

    public QueryScriptLines(final String owningDb, final String objName) {
        scriptedObjName = objName;
        scriptObj = new ScriptedObjects(owningDb);
    }

    public final String getParameterizedSQL() {
        return SQL;
    }

    public final void processStatement(final PreparedStatement stmt) throws SQLException {
        stmt.setString(Parameter.ONE.index(), scriptedObjName);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String line = rs.getString("text");
            scriptObj.add(line);
        }
    }

    public final DbObjects<String> result() {
        return scriptObj;
    }

}
