package com.googlecode.hdbc.dbmigrate.validator;

import java.util.EnumMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.googlecode.hdbc.dbmigrate.Key;

public class MigrationScriptNameValidator implements IInputValidator {

    public final boolean validate(final String response, final EnumMap<Key, String> params) {
        Pattern p = Pattern.compile("[a-zA-Z0-9_-]+");
        Matcher m = p.matcher(response);
        return m.find();
    }

}
