package com.googlecode.hdbc.dbmigrate.processor;

import java.io.IOException;
import java.util.List;

import com.googlecode.hdbc.dbmigrate.MenuItem;

public class TopLevelMenuResponseProcessor implements IInputProcessor {

    public final void process(final String input, final List<MenuItem> items) throws IOException {
        if ("1".equals(input)) {
            items.get(0).runSubMenu();
        } else {
            items.get(1).runSubMenu();
        }

    }

}
