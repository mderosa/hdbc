package com.googlecode.hdbc.dbmigrate.validator;

import static org.junit.Assert.*;

import java.util.EnumMap;

import org.junit.Test;

import com.googlecode.hdbc.dbmigrate.Key;


public class GoToDbVersionValidatorTest {

    @Test
    public final void testValidateShouldReturnFalse() {
        EnumMap<Key, String> params = new EnumMap<Key, String>(Key.class);
        params.put(Key.CURRENT_DB_VERSION, "12");
        GoToDbVersionValidator val = new GoToDbVersionValidator();
        boolean actual = val.validate("10", params);
        assertFalse(actual);
    }
}
