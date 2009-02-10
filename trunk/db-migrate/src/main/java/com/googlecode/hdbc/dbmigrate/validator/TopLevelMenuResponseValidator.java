package com.googlecode.hdbc.dbmigrate.validator;

public class TopLevelMenuResponseValidator implements IInputValidator {

    @Override
    public boolean validate(String response) {
        if ("1".equals(response) || "2".equals(response)) {
            return true;
        } else {
            return false;
        }
    }

}
