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
        "00001-do_ScriptOne.sql",
        "00044-do_Script44.sql",
        "00003-do_ScriptThree.sql",
        "00002-do_ScriptTwo.sql",};
        String actual = proc.nextFileIndex(files);
        assertEquals("45", actual);
    }
}
