package com.googlecode.hdbc.dbdiff.model;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsEqual.equalTo;

public class UserObjectsTest {
	private static final String DB_NAME = "adb";
	private static final String DB_TABLE_IDENTIFIER = "table";
	private UserObjectDefinition sum1 = new UserObjectDefinition("name1", DB_TABLE_IDENTIFIER);
	private UserObjectDefinition sum2 = new UserObjectDefinition("name2", DB_TABLE_IDENTIFIER);
	private UserObjectDefinition sum3 = new UserObjectDefinition("name3", DB_TABLE_IDENTIFIER);

	@Test
	public final void twoEmptyObjectsShouldBeEqual() {
		UserObjects obj1 = new UserObjects(DB_NAME);
		UserObjects obj2 = new UserObjects(DB_NAME);
		assertThat(obj1, is(equalTo(obj2)));
	}

	@Test
	public final void twoObjectsWithIdenticalContentAreEqual() {
		DbObjects<UserObjectDefinition> obj1 = new UserObjects(DB_NAME).add(sum1).add(sum2);
		DbObjects<UserObjectDefinition> obj2 = new UserObjects(DB_NAME).add(sum1).add(sum2);
		assertThat(obj1,is(equalTo(obj2)));
	}

	@Test
	public final void anObjectContainingASubsetOfAnotherObjectShouldNotEqual() {
		DbObjects<UserObjectDefinition> objs1 = new UserObjects(DB_NAME).add(sum1).add(sum2);
		DbObjects<UserObjectDefinition> objs2 = new UserObjects(DB_NAME).add(sum1).add(sum2).add(sum3);
		assertThat(objs1, not(equalTo(objs2)));
		assertThat(objs2, not(equalTo(objs1)));
	}
}
