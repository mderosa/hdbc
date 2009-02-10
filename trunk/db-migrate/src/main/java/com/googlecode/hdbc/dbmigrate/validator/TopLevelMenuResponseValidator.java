package com.googlecode.hdbc.dbmigrate.validator;

public class TopLevelMenuResponseValidator implements IInputValidator {

    public final boolean validate(final String response) {
        return "1".equals(response) || "2".equals(response);
    }

}
