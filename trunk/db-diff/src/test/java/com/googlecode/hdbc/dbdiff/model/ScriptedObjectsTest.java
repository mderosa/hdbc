package com.googlecode.hdbc.dbdiff.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ScriptedObjectsTest {
    private ScriptedObjects obj1;
    private ScriptedObjects obj2;

    @Before
    public final void setUp() {
        String dbName = "db";
        String line = "line";
        obj1 = new ScriptedObjects(dbName);
        obj1.add(line);
        obj2 = new ScriptedObjects(dbName);
        obj2.add(line);
    }

    @Test
    public final void testDefaultObjAreEqual() {
        assertEquals(obj1, obj2);
    }

    @Test
    public final void testNotEqualBecauseOfDiffSize() {
        obj1.add("line2");
        assertFalse(obj1.equals(obj2));
    }

    @Test
    public final void testNotEqualBecauseOfDiffContents() {
        obj1.add("to be or not");
        obj2.add("to be or no");
        assertFalse(obj1.equals(obj2));
    }
}
