package com.googlecode.hdbc.dbmigrate.processor;

import java.io.IOException;
import java.util.EnumMap;
import java.util.List;

import com.googlecode.hdbc.dbmigrate.Key;
import com.googlecode.hdbc.dbmigrate.MenuItem;

public class CurrentDbVersionProcessor implements IInputProcessor {

    @Override
    public final void process(final String input, final List<MenuItem> items,
            final EnumMap<Key, String> params) throws IOException {
        params.put(Key.CURRENT_DB_VERSION, input.trim());
        MenuItem next = items.get(0);
        next.runSubMenu(params);
    }

}
