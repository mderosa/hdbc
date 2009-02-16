package com.googlecode.hdbc.dbmigrate.validator;

import static org.junit.Assert.*;

import org.junit.Test;

public class CurrentDbVersionValidatorTest {

    @Test
    public final void testValidateShouldReturnFalse1() {
        CurrentDbVersionValidator val = new CurrentDbVersionValidator();
        boolean actual = val.validate("notANumber", null);
        assertFalse(actual);
    }

    @Test
    public final void testValidateShouldReturnFalse2() {
        CurrentDbVersionValidator val = new CurrentDbVersionValidator();
        boolean actual = val.validate("-1", null);
        assertFalse(actual);
    }
}
