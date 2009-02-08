package com.googlecode.hdbc.dbmigrate.processor;


public class NullProcessor implements IInputProcessor {

    @Override
    public final String process(final String input) {
        return null;
    }

}
