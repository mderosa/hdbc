package com.googlecode.hdbc.dbmigrate.processor;

import java.io.IOException;
import java.util.List;

import com.googlecode.hdbc.dbmigrate.MenuItem;

public class TopLevelMenuResponseProcessor implements IInputProcessor {

    @Override
    public void process(String input, List<MenuItem> items) throws IOException {
        if ("1".equals(input)) {
            items.get(0).runSubMenu();
        } else {
            items.get(1).runSubMenu();
        }

    }

}
