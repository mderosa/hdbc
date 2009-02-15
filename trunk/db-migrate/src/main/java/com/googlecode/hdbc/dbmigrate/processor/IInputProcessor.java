package com.googlecode.hdbc.dbmigrate.processor;

import java.io.IOException;
import java.util.EnumMap;
import java.util.List;

import com.googlecode.hdbc.dbmigrate.Key;
import com.googlecode.hdbc.dbmigrate.MenuItem;

public interface IInputProcessor {

    void process(String input, List<MenuItem> items, EnumMap<Key, String> params) throws IOException;
}
