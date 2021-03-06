package com.googlecode.hdbc.dbmigrate;

import java.io.IOException;
import java.util.EnumMap;

public final class MenuItem {
    private final String question;
    private final Menu subMenu;

    private MenuItem(final String topicQuestion, final Menu menu) {
        this.question = topicQuestion;
        this.subMenu = menu;
    }

    public static MenuItem item(final String question, final Menu subMenu) {
        return new MenuItem(question, subMenu);
    }

    public void runSubMenu(final EnumMap<Key, String> params) throws IOException {
        subMenu.run(params);
    }

    @Override
    public String toString() {
        return question;
    }
}
