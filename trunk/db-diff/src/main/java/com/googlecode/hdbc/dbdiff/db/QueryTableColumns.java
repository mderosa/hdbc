package com.googlecode.hdbc.dbdiff.db;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.googlecode.hdbc.dbdiff.model.ColumnDefinition;
import com.googlecode.hdbc.dbdiff.model.TabularObjects;

public class QueryTableColumns implements IQueryDefinition<ColumnDefinition> {
    private final String tableName;
    private final TabularObjects result;
    private static final String SQL = "SELECT column_name, data_type, data_length, data_precision, data_scale, nullable " +
        "FROM user_tab_columns " +
        "WHERE table_name = ? ";

    public QueryTableColumns(final String owningDb, final String table) {
        tableName = table;
        result = new TabularObjects(owningDb);
    }

    public final String getParameterizedSQL() {
        return SQL;
    }

    public final void processStatement(final PreparedStatement stmt) throws SQLException {
        stmt.setString(Parameter.ONE.index(), tableName);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String columnName = rs.getString("column_name");
            String dataType = rs.getString("data_type");
            int dataLength = rs.getInt("data_length");
            BigDecimal dataPrecision = rs.getBigDecimal("data_precision");
            BigDecimal dataScale = rs.getBigDecimal("data_scale");
            String nullable = rs.getString("nullable");
            result.add(new ColumnDefinition(columnName, dataType, dataLength,
                    dataPrecision, dataScale, nullable));
        }
    }

    public final TabularObjects result() {
        return result;
    }

}
