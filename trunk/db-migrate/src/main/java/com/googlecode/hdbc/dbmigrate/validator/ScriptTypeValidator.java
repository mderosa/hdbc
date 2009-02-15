package com.googlecode.hdbc.dbmigrate.validator;

import java.util.EnumMap;

import com.googlecode.hdbc.dbmigrate.Key;

public class ScriptTypeValidator implements IInputValidator {

    @Override
    public final boolean validate(final String response, final EnumMap<Key, String> params) {
        return "ddl".equalsIgnoreCase(response) || "dml".equalsIgnoreCase(response);
    }
}
