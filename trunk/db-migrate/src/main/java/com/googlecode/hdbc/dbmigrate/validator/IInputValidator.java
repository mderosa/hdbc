package com.googlecode.hdbc.dbmigrate.validator;

import java.util.EnumMap;

import com.googlecode.hdbc.dbmigrate.Key;

public interface IInputValidator {

    boolean validate(String response, EnumMap<Key, String> params);

}
