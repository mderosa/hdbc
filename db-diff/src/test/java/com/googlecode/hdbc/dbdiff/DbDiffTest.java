package com.googlecode.hdbc.dbdiff;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import com.googlecode.hdbc.dbdiff.DbDiff.Check;
import com.googlecode.hdbc.dbdiff.db.IDatabase;
import com.googlecode.hdbc.dbdiff.model.ColumnDefinition;
import com.googlecode.hdbc.dbdiff.model.DbObjects;
import com.googlecode.hdbc.dbdiff.model.TabularObjects;
import com.googlecode.hdbc.dbdiff.model.UserObjectDefinition;
import com.googlecode.hdbc.dbdiff.model.UserObjects;

@RunWith(JMock.class)
public class DbDiffTest {
	private DbDiff diff;
	private Mockery context;

	@Before
	public final void setUp() {
		diff = new DbDiff();
		context = new JUnit4Mockery();
	}

	@Test
	public final void shouldIndicateIdenticalUserObjects() {
		final IDatabase testDb = context.mock(IDatabase.class, "test");
		final IDatabase refDb = context.mock(IDatabase.class, "ref");

		context.checking(new Expectations() {{
			oneOf(testDb).selectUserObjects();
				will(returnValue(new UserObjects("db1")));
			oneOf(refDb).selectUserObjects();
				will(returnValue(new UserObjects("db2")));
		}});
		boolean actual = this.diff.diffUserObjects(testDb, refDb, Check.ObjectsExist);
		assertThat(actual, is(equalTo(true)));
	}

	@Test
	public final void shouldIndicateIdenticalUserObjects2() {
		final IDatabase testDb = context.mock(IDatabase.class, "test");
		final IDatabase refDb = context.mock(IDatabase.class, "ref");
		final DbObjects<UserObjectDefinition> test = new UserObjects("db1")
			.add(new UserObjectDefinition("USERS", "TABLE"))
			.add(new UserObjectDefinition("ROLES", "TABLE"));
		final DbObjects<UserObjectDefinition> ref = new UserObjects("db2")
			.add(new UserObjectDefinition("USERS", "TABLE"))
			.add(new UserObjectDefinition("ROLES", "TABLE"));

		context.checking(new Expectations() {{
			oneOf(testDb).selectUserObjects();
				will(returnValue(test));
			oneOf(refDb).selectUserObjects();
				will(returnValue(ref));
		}});
		boolean actual = this.diff.diffUserObjects(testDb, refDb, Check.ObjectsExist);
		assertThat(actual, is(equalTo(true)));
	}

	@Test
	public final void shouldIndicateDifferentUserObjects() {
		final IDatabase testDb = context.mock(IDatabase.class, "test");
		final IDatabase refDb = context.mock(IDatabase.class, "ref");
		final DbObjects<UserObjectDefinition> test = new UserObjects("db1")
			.add(new UserObjectDefinition("USERS", "TABLE"))
			.add(new UserObjectDefinition("ROLES", "TABLE"));
		final DbObjects<UserObjectDefinition> ref = new UserObjects("db2")
			.add(new UserObjectDefinition("USERS", "TABLE"));

		context.checking(new Expectations() {{
			oneOf(testDb).selectUserObjects();
				will(returnValue(test));
			oneOf(refDb).selectUserObjects();
				will(returnValue(ref));
		}});
		boolean actual = this.diff.diffUserObjects(testDb, refDb, Check.ObjectsExist);
		assertThat(actual, is(equalTo(false)));
	}

	@Test
	public final void shouldIndicateIdenticalTablularObjs() {
		final IDatabase testDb = context.mock(IDatabase.class, "test");
		final IDatabase refDb = context.mock(IDatabase.class, "ref");
		final DbObjects<ColumnDefinition> test = new TabularObjects("db1")
			.add(new ColumnDefinition("id", "int", 32, null, null, "Y"))
			.add(new ColumnDefinition("name", "varchar", 64, null, null, "Y"));
		final DbObjects<ColumnDefinition> ref = new TabularObjects("db2")
			.add(new ColumnDefinition("id", "int", 32, null, null, "Y"))
			.add(new ColumnDefinition("name", "varchar", 64, null, null, "Y"));

		context.checking(new Expectations() {{
			oneOf(testDb).selectTabularObject("user");
				will(returnValue(test));
			oneOf(refDb).selectTabularObject("user");
				will(returnValue(ref));
		}});
		boolean actual = this.diff.diffTabularObject(testDb, refDb, "user");
		assertThat(actual, is(equalTo(true)));
	}

	@Test
	public void shouldIndicateDifferentTablularObjs() {
		final IDatabase testDb = context.mock(IDatabase.class, "test");
		final IDatabase refDb = context.mock(IDatabase.class, "ref");
		final DbObjects<ColumnDefinition> test = new TabularObjects("db1")
			.add(new ColumnDefinition("id", "int", 32, null, null, "Y"))
			.add(new ColumnDefinition("name", "varchar", 64, null, null, "Y"));
		final DbObjects<ColumnDefinition> ref = new TabularObjects("db2");

		context.checking(new Expectations() {{
			oneOf(testDb).selectTabularObject("user");
				will(returnValue(test));
			oneOf(refDb).selectTabularObject("user");
				will(returnValue(ref));
		}});
		boolean actual = this.diff.diffTabularObject(testDb, refDb, "user");
		assertThat(actual, is(equalTo(false)));
	}
}
