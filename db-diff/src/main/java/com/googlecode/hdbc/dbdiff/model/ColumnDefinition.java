package com.googlecode.hdbc.dbdiff.model;

import java.math.BigDecimal;

public class ColumnDefinition {
	private final Nullable nullable;
    private final String name;
	private final String dataType;
	private final Integer length;
	private final Integer precision;
	private final Integer scale;
	private static final String SEPARATOR = ",";

	public enum Nullable {
		NULL,
		NOT_NULL
	}

	public ColumnDefinition(final String namE, final String type,
	        final Integer len, final BigDecimal prec, final BigDecimal scl, final String nullablE) {
	    if (nullablE.equals("Y")) {
	        nullable = Nullable.NULL;
	    } else {
	        nullable = Nullable.NOT_NULL;
	    }
		name = namE;
		dataType = type;
		length = len;
		if (prec != null) {
		    precision = prec.intValue();
		} else {
		    precision = null;
		}
		if (scl != null) {
		    scale = scl.intValue();
		} else {
		    scale = null;
		}
	}

	public final Nullable getNullable() {
        return nullable;
    }
    public final Integer getPrecision() {
        return precision;
    }
    public final Integer getScale() {
        return scale;
    }
	public final String getName() {
		return name;
	}
	public final String getDataType() {
		return dataType;
	}
	public final int getLength() {
		return length;
	}

	@Override
	public final boolean equals(final Object obj) {
		if (obj == null || !(obj instanceof ColumnDefinition)) {
			return false;
		}

		ColumnDefinition temp = (ColumnDefinition) obj;
		return temp.getDataType().equals(this.dataType) &&
			temp.getName().equals(this.name);
	}

	@Override
	public final int hashCode() {
		return this.getName().hashCode() + this.getDataType().hashCode();
	}

	@Override
	public final String toString() {
	    StringBuilder temp = new StringBuilder();
	    return temp.append("column:")
	        .append(this.getName())
	        .append(SEPARATOR)
	        .append("type:")
	        .append(this.getDataType())
	        .append(SEPARATOR)
	        .append("length:")
	        .append(this.getLength())
	        .append(SEPARATOR)
	        .append("precision:")
	        .append(this.getPrecision())
	        .append(SEPARATOR)
	        .append("scale:")
	        .append(this.getScale())
	        .toString();
	}
}
