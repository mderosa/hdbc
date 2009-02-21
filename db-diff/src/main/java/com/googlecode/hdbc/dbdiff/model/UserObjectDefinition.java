package com.googlecode.hdbc.dbdiff.model;

public class UserObjectDefinition {
	private final String objectType;
	private final String objectName;

	public UserObjectDefinition(final String objName, final String objType) {
		objectName = objName;
		objectType = objType;
	}

	public final String getObjectName() {
		return objectName;
	}

	public final String getObjectType() {
		return objectType;
	}

	public final boolean isTabularObject() {
		return this.objectType.equals("TABLE") || this.objectType.equals("VIEW");
	}

	public final boolean isScriptedObject() {
	    return this.objectType.equals("FUNCTION") || this.objectType.equals("PROCEDURE");
	}

	@Override
	public final boolean equals(final Object obj) {
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserObjectDefinition)) {
			return false;
		}
		UserObjectDefinition temp = (UserObjectDefinition) obj;
		return temp.getObjectName().equals(objectName) &&
			temp.getObjectType().equals(objectType);
	}

	@Override
	public final int hashCode() {
		return objectName.hashCode() + objectType.hashCode();
	}

	@Override
	public final String toString() {
	    StringBuilder temp = new StringBuilder();
	    return temp.append(this.objectName)
	        .append(" (")
	        .append(this.objectType)
	        .append(")")
	        .toString();
	}
}
