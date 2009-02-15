package com.googlecode.hdbc.dbmigrate.processor;

import java.util.EnumMap;
import java.util.List;

import com.googlecode.hdbc.dbmigrate.Key;
import com.googlecode.hdbc.dbmigrate.MenuItem;

public class NullProcessor implements IInputProcessor {

    public final void process(final String input, final List<MenuItem> items,
            final EnumMap<Key, String> params) {

    }

}
