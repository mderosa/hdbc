package com.googlecode.hdbc.dbmigrate.processor;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MigrationScriptNameProcessorTest {
    private MigrationScriptNameProcessor proc;

    @Before
    public final void setUp() {
        proc = new MigrationScriptNameProcessor();
    }

    @Test
    public final void rawInputNameShouldStartCapitalized() {
        String simpleName = "test";
        String actual = proc.preprocessRawInputName(simpleName);
        assertEquals(simpleName, actual);
    }

    @Test
    public final void rawInputNameShouldntHaveDashOrUnderscores() {
        String actual = proc.preprocessRawInputName("this-is_an_example");
        assertEquals("thisIsAnExample", actual);
    }

    @Test
    public final void testNextFileIndex() {
        String[] files = new String[] {
        "1-do_ScriptOne.sql",
        "44-do_Script44.sql",
        "3-do_ScriptThree.sql",
        "2-do_ScriptTwo.sql",};
        String actual = proc.nextFileIndex(files);
        assertEquals("45", actual);
    }
}
