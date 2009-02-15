package com.googlecode.hdbc.dbmigrate.validator;

import java.util.EnumMap;

import com.googlecode.hdbc.dbmigrate.Key;

public class CurrentDbVersionValidator implements IInputValidator {

    @Override
    public final boolean validate(final String response, final EnumMap<Key, String> params) {
        boolean ok;
        try {
            int currentDbVersion = Integer.parseInt(response);
            ok = currentDbVersion >= 0;
        } catch (NumberFormatException e) {
            ok = false;
        }
        return ok;
    }

}
