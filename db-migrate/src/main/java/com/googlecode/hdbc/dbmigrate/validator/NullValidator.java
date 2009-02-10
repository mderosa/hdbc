package com.googlecode.hdbc.dbmigrate.validator;


public class NullValidator implements IInputValidator {

    @Override
    public final boolean validate(final String response) {
        return true;
    }

}
