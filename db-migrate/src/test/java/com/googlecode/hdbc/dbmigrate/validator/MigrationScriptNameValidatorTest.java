package com.googlecode.hdbc.dbmigrate.validator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MigrationScriptNameValidatorTest {
    private MigrationScriptNameValidator val;

    @Before
    public final void setUp() {
        val = new MigrationScriptNameValidator();
    }
    @Test
    public final void validateShouldNotPassAnEmptyPaddedString() {
        assertFalse(val.validate("    ", null));
    }

    @Test
    public final void validateShouldNotPassNonAlphaNumericStrings() {
        assertFalse(val.validate("&%$", null));
    }

    @Test
    public final void validateNameCanIncludeNumbers() {
        assertTrue(val.validate("9888877666", null));
    }
}
