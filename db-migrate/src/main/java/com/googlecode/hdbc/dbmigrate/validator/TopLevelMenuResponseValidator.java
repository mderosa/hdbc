package com.googlecode.hdbc.dbmigrate.validator;

import java.util.EnumMap;

import com.googlecode.hdbc.dbmigrate.Key;

public class TopLevelMenuResponseValidator implements IInputValidator {

    public final boolean validate(final String response, final EnumMap<Key, String> params) {
        return "1".equals(response) || "2".equals(response) || "3".equals(response);
    }

}
