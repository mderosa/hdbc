package com.googlecode.hdbc.dbmigrate;

public final class MenuItem {

    private MenuItem() {}

    public static MenuItem item(final String question, final Menu subMenu) {
        return new MenuItem();
    }
}
