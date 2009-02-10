package com.googlecode.hdbc.dbmigrate.processor;

import java.io.IOException;
import java.util.List;
import com.googlecode.hdbc.dbmigrate.MenuItem;

public class MigrationScriptNameProcessor implements IInputProcessor {

    public void process(final String input, final List<MenuItem> items) throws IOException {

    }

    protected final String preprocessRawInputName(final String input) {
        StringBuilder temp = new StringBuilder();
        String[] parts = input.split("[-_]");
        for (String part : parts) {
            String head = part.substring(0, 1);
            String tail = part.substring(1);
            head = head.toUpperCase();
            temp.append(head + tail);
        }
        return temp.toString();
    }

    protected final String nextFileIndex(final List<String> fileNames) {
        return "";
    }
}
