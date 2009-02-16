package com.googlecode.hdbc.dbmigrate.validator;

import java.util.EnumMap;

import com.googlecode.hdbc.dbmigrate.Key;

public class GoToDbVersionValidator implements IInputValidator {

    public final boolean validate(final String response, final EnumMap<Key, String> params) {
        CurrentDbVersionValidator val = new CurrentDbVersionValidator();
        boolean ok = val.validate(response, params);
        if (ok) {
            String currentVersion = params.get(Key.CURRENT_DB_VERSION);
            int curVersion = Integer.parseInt(currentVersion);
            int gotoVersion = Integer.parseInt(response);
            ok = curVersion < gotoVersion;
        }
        return ok;
    }

}
