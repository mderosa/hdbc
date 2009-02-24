package com.googlecode.hdbc.dbmigrate.processor;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.EnumMap;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.hamcrest.core.IsEqual.equalTo;
import com.googlecode.hdbc.dbmigrate.Key;
import com.googlecode.hdbc.dbmigrate.io.IFileProvider;

@RunWith(JMock.class)
public class ScriptTypeProcessorTest {
    private Mockery context;

    @Before
    public final void setUp() {
        context = new JUnit4Mockery();
    }

    @Test
    public final void testScriptNameRegex() {
        String temp = "this is ${script_name} something";
        String actual = temp.replaceAll(ScriptTypeProcessor.SCRIPT_NAME_RGX, "more");
        assertEquals("this is more something", actual);
    }

    @Test
    public final void testVersionRegex() {
        String temp = "the version ${version_no} is such";
        String actual = temp.replaceAll(ScriptTypeProcessor.VERSION_RGX, "10");
        assertEquals("the version 10 is such", actual);
    }

    @Test
    public final void testProcess() throws IOException {
        final String userTypeSelection = "ddl";
        final IFileProvider mockProvider = context.mock(IFileProvider.class);
        context.checking(new Expectations() {{
            oneOf(mockProvider).templateContent(with(equalTo(userTypeSelection)));
            will(returnValue("version goto ${version_no} "));
            oneOf(mockProvider).writeDoFile(with(equalTo("00005-do_Test.sql")), with(equalTo("version goto 5 ")));
            oneOf(mockProvider).writeUndoFile(with(equalTo("00005-undo_Test.sql")), with(equalTo("version goto 4 ")));
        }});

        ScriptTypeProcessor processor = new ScriptTypeProcessor(mockProvider);
        EnumMap<Key, String> params = new EnumMap<Key, String>(Key.class);
        params.put(Key.MIGRATION_NAME, "Test");
        params.put(Key.MIGRATION_NUMBER, "5");
        processor.process(userTypeSelection, null, params);
    }

}
