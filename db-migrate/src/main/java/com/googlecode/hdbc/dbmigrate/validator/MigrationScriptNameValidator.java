package com.googlecode.hdbc.dbmigrate.validator;

import java.util.regex.Pattern;

public class MigrationScriptNameValidator implements IInputValidator {

    public final boolean validate(final String response) {
        //Pattern p = new Pattern();

        if ("".equals(response)) {
            return false;
        } else {
            return true;
        }
    }

}
