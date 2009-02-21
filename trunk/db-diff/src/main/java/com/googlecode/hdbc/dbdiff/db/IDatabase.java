package com.googlecode.hdbc.dbdiff.db;

import com.googlecode.hdbc.dbdiff.model.ScriptedObjects;
import com.googlecode.hdbc.dbdiff.model.TabularObjects;
import com.googlecode.hdbc.dbdiff.model.UserObjects;

public interface IDatabase {

	UserObjects selectUserObjects();

	TabularObjects selectTabularObject(String name);

	ScriptedObjects selectScriptedObject(String name);
}
