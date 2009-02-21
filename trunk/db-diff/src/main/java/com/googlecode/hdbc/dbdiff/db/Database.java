package com.googlecode.hdbc.dbdiff.db;

import java.sql.SQLException;
import javax.sql.DataSource;

import com.googlecode.hdbc.dbdiff.model.ColumnDefinition;
import com.googlecode.hdbc.dbdiff.model.ScriptedObjects;
import com.googlecode.hdbc.dbdiff.model.TabularObjects;
import com.googlecode.hdbc.dbdiff.model.UserObjectDefinition;
import com.googlecode.hdbc.dbdiff.model.UserObjects;

public class Database implements IDatabase {
    private final DataSource dataSource;
    private final String databaseName;

    public Database(final String dbName, final DataSource ds) {
        databaseName = dbName;
        dataSource = ds;
    }

    public final TabularObjects selectTabularObject(final String tableName) {
        try {
            QueryExecutionTemplate<ColumnDefinition> template = new QueryExecutionTemplate<ColumnDefinition>();
            QueryTableColumns query = new QueryTableColumns(databaseName, tableName);
            return (TabularObjects) template.executeQuery(query, dataSource);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public final UserObjects selectUserObjects() {
        try {
            QueryExecutionTemplate<UserObjectDefinition> template = new QueryExecutionTemplate<UserObjectDefinition>();
            QueryUserObjects query = new QueryUserObjects(databaseName);
            return (UserObjects) template.executeQuery(query, dataSource);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public final ScriptedObjects selectScriptedObject(final String scriptObjName) {
        try {
            QueryExecutionTemplate<String> template = new QueryExecutionTemplate<String>();
            QueryScriptLines query = new QueryScriptLines(databaseName, scriptObjName);
            return (ScriptedObjects) template.executeQuery(query, dataSource);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
