package com.googlecode.hdbc.dbmigrate;

import java.io.IOException;
import java.util.EnumMap;

public final class MenuItem {
    private final String question;
    private final Menu subMenu;
    private final EnumMap<Key, String> passedParams = new EnumMap<Key, String>(Key.class);

    private MenuItem(final String topicQuestion, final Menu menu) {
        this.question = topicQuestion;
        this.subMenu = menu;
    }

    public static MenuItem item(final String question, final Menu subMenu) {
        return new MenuItem(question, subMenu);
    }

    public void passParameter(final Key key, final String value) {
        passedParams.put(key, value);
    }

    public void runSubMenu() throws IOException {
        subMenu.run();
    }

    @Override
    public String toString() {
        return question;
    }
}
