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
        test.add("2-do_This");
        test.add("1-do_That");
        test.add("22-do_Another");
        test.add("11-do_Last");
    }

    @Test
    public final void testThatWeCanFiterAFileList() {
        GoToDbVersionProcessor proc = new GoToDbVersionProcessor();
        String[] array = test.toArray(new String[0]);
        List<String> actual = proc.filteredFileList(array, 2, 15);
        assertEquals(actual.size(), 2);
        assertTrue(actual.contains("2-do_This"));
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
