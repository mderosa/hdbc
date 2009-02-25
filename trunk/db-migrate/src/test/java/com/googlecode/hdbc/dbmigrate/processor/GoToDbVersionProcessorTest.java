package com.googlecode.hdbc.dbmigrate.processor;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GoToDbVersionProcessorTest {
    private List<String> test;

    @Before
    public final void setUp() {
        test = new ArrayList<String>();
        test.add("00002-do_This");
        test.add("00001-do_That");
        test.add("00022-do_Another");
        test.add("00011-do_Last");
    }

    @Test
    public final void testThatWeCanFiterAFileList() {
        GoToDbVersionProcessor proc = new GoToDbVersionProcessor();
        String[] array = test.toArray(new String[0]);
        List<String> actual = proc.filteredFileList(array, 2, 15);
        assertEquals(actual.size(), 1);
        assertFalse(actual.contains("00002-do_This"));
    }

    @Test
    public final void testThatWeCanFiterAFileList2() {
        GoToDbVersionProcessor proc = new GoToDbVersionProcessor();
        ArrayList<String> tst = new ArrayList<String>();
        tst.add("00009-do_That");
        tst.add("00010-do_Another");
        tst.add("00011-do_Last");
        String[] array = tst.toArray(new String[0]);
        List<String> actual = proc.filteredFileList(array, 10, 11);
        assertEquals(actual.size(), 1);
        assertTrue(actual.contains("00011-do_Last"));
    }

    @Test
    public final void testThatWeCanSortAFileList() {
        GoToDbVersionProcessor proc = new GoToDbVersionProcessor();
        List<String> actual = proc.sortedFileList(test);
        assertEquals(test.size(), actual.size());
        assertEquals(test.get(1), actual.get(0));
        assertEquals(test.get(2), actual.get(3));
        assertEquals(test.get(0), actual.get(1));
    }
}
