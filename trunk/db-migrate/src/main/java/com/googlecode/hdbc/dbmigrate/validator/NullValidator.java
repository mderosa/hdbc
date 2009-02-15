package com.googlecode.hdbc.dbmigrate.validator;

import java.util.EnumMap;

import com.googlecode.hdbc.dbmigrate.Key;


public class NullValidator implements IInputValidator {

    public final boolean validate(final String response, final EnumMap<Key, String> params) {
        return true;
    }

}
