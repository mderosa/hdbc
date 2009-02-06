package com.googlecode.hdbc.dbdiff.model;

import java.util.Collection;

public class DbObjects<T> {
	private Collection<T> constituentObjects;
	private final String owningDatabase;

	public DbObjects(final String dbName, final Collection<T> constituents) {
		owningDatabase = dbName;
		constituentObjects = constituents;
	}

	public final String getOwningDatabase() {
		return owningDatabase;
	}

	public final Collection<T> getConstituentObjects() {
		return constituentObjects;
	}

	public final DbObjects<T> add(final T obj) {
		this.constituentObjects.add(obj);
		return this;
	}

	public final boolean equalContents(final Collection<T> objs) {
		if (objs == null) {
			return false;
		}

		return new Analyze<T>("")
			.collections(this.constituentObjects, objs)
			.areEqual();
	}

	@Override
	public final int hashCode() {
		return this.constituentObjects.hashCode();
	}
}
