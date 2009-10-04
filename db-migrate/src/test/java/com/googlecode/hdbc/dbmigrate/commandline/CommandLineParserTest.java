package com.googlecode.hdbc.dbmigrate.commandline;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import com.googlecode.hdbc.dbmigrate.io.IDatabaseProvider;
import com.googlecode.hdbc.dbmigrate.io.IFileProvider;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;

@RunWith(JMock.class)
public class CommandLineParserTest {
	private CommandLineParser parser;
	private IDatabaseProvider db;
	private IFileProvider fs;
	private Mockery context;
	
	private static final String[] files = new String[] {
			"00020-do_a.sql",
			"00025-do_b.sql",
			"00024-do_c.sql",
			"00021-do_d.sql",
			"00023-do_e.sql",
			"00021-do_f.sql"
		}; 
	
	@Before
	public void setUp() {
		context = new JUnit4Mockery();
		db = context.mock(IDatabaseProvider.class);
		fs = context.mock(IFileProvider.class);
		parser = new CommandLineParser(db, fs);
	}
	
	/**
	 * args = []
	 */
	@Test
	public void testInteractiveModeStdCase() {
		RunMode actual = parser.parse(new String[] {});
		assertTrue(actual instanceof InteractiveMode);
	}
	
	/**
	 * contains "^-t\d*" args == False
	 */
	@Test 
	public void testInteractiveMode2() {
		RunMode actual = parser.parse(new String[] {"10-t", "-a"});
		assertTrue(actual instanceof InteractiveMode);
	}
	
	/**
	 * contains "^-t" args = True
	 * <p>
	 * In this case we want the database to be upgraded to the highest
	 * version available 
	 */
	@Test
	public void testAutoModeStdCase() {
		context.checking(new Expectations() {{
			oneOf(db).getCurrentVersion();
			will(returnValue(23));
			
			oneOf(fs).migrationFileList();
			will(returnValue(files));
		}});

		RunMode actual = parser.parse(new String[] {"-t"});
		assertTrue(actual instanceof AutomatedMode);
		assertEquals(23, actual.getFromVersion());
		assertEquals(25, actual.getToVersion());
	}
	
	/**
	 * contains "^-t(\d*)" args == True && (\d*) <= version max
	 */
	@Test 
	public void testAutoMode2() {
		context.checking(new Expectations() {{
			oneOf(db).getCurrentVersion();
			will(returnValue(23));
			
			oneOf(fs).migrationFileList();
			will(returnValue(files));
		}});

		RunMode actual = parser.parse(new String[] {"-t24"});
		assertTrue(actual instanceof AutomatedMode);
		assertEquals(23, actual.getFromVersion());
		assertEquals(24, actual.getToVersion());
	}
	
	/**
	 * contains "^-t(\d*) args == True && (\d*) > version max
	 * <p>
	 * In this case we want to default the to variable to version max
	 */
	@Test
	public void testAutoMode3() {
		context.checking(new Expectations() {{
			oneOf(db).getCurrentVersion();
			will(returnValue(23));
			
			oneOf(fs).migrationFileList();
			will(returnValue(files));
		}});
		
		RunMode actual = parser.parse(new String[] {"-t532"});
		assertTrue(actual instanceof AutomatedMode);
		assertEquals(23, actual.getFromVersion());
		assertEquals(25, actual.getToVersion());
	}
	
}
