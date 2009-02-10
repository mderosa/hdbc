package com.googlecode.hdbc.dbmigrate.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MigrationScriptNameValidator implements IInputValidator {

    public final boolean validate(final String response) {
        Pattern p = Pattern.compile("[a-zA-Z0-9_-]+");
        Matcher m = p.matcher(response);
        return m.find();
    }

}
