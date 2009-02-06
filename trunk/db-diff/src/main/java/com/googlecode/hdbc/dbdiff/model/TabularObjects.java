package com.googlecode.hdbc.dbdiff.model;

import java.util.HashSet;

public class TabularObjects extends DbObjects<ColumnDefinition> {

	public TabularObjects(final String dbName) {
		super(dbName, new HashSet<ColumnDefinition>());
	}

	@Override
	public final boolean equals(final Object obj) {
		if (obj == null || !(obj instanceof UserObjects)) {
			return false;
		}
		TabularObjects temp = (TabularObjects) obj;
		return this.equalContents(temp.getConstituentObjects());
	}

}
