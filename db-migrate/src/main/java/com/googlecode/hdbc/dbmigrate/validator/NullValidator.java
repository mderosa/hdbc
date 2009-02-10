package com.googlecode.hdbc.dbmigrate.validator;


public class NullValidator implements IInputValidator {

    public final boolean validate(final String response) {
        return true;
    }

}
