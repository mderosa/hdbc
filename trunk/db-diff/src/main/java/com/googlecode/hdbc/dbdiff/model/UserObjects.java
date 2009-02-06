package com.googlecode.hdbc.dbdiff.model;

import java.util.HashSet;

public class UserObjects extends DbObjects<UserObjectDefinition> {

	public UserObjects(final String dbName) {
		super(dbName, new HashSet<UserObjectDefinition>());
	}

	@Override
	public final boolean equals(final Object obj) {
		if (obj == null || !(obj instanceof UserObjects)) {
			return false;
		}
		UserObjects temp = (UserObjects) obj;
		return this.equalContents(temp.getConstituentObjects());
	}
}
