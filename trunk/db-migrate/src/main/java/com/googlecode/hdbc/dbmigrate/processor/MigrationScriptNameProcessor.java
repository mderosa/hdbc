package com.googlecode.hdbc.dbmigrate.processor;

import java.io.IOException;
import java.util.EnumMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.googlecode.hdbc.dbmigrate.Key;
import com.googlecode.hdbc.dbmigrate.MenuItem;
import com.googlecode.hdbc.dbmigrate.io.FileSystemFileProvider;
import com.googlecode.hdbc.dbmigrate.io.IFileProvider;

public class MigrationScriptNameProcessor implements IInputProcessor {
    private IFileProvider provider;

    public MigrationScriptNameProcessor() {
        provider = new FileSystemFileProvider();
    }

    public MigrationScriptNameProcessor(final IFileProvider prvdr) {
        provider = prvdr;
    }

    public final void process(final String input, final List<MenuItem> items,
            final EnumMap<Key, String> params) throws IOException {
        String finalFileName = this.preprocessRawInputName(input);
        String migrationNumber = this.nextFileIndex(provider.migrationFileList());
        MenuItem nextItem = items.get(0);
        params.put(Key.MIGRATION_NAME, finalFileName);
        params.put(Key.MIGRATION_NUMBER, migrationNumber);
        nextItem.runSubMenu(params);
    }

    protected final String preprocessRawInputName(final String input) {
        StringBuilder temp = new StringBuilder();
        String[] parts = input.split("[-_]");
        boolean beginningOfInput = true;
        for (String part : parts) {
            String head = part.substring(0, 1);
            String tail = part.substring(1);
            if (!beginningOfInput) {
                head = head.toUpperCase();
            }
            temp.append(head + tail);
            beginningOfInput = false;
        }
        return temp.toString();
    }

    protected final String nextFileIndex(final String[] fileNames) {
        Pattern pat = Pattern.compile("^([0-9]+)-do_[a-zA-Z0-9]+");
        int max = 1;
        for (String fileName : fileNames) {
            Matcher match = pat.matcher(fileName);
            if (match.find()) {
                String prefix = match.group(1);
                int temp = Integer.parseInt(prefix);
                if (temp > max) {
                    max = temp;
                }
            }
        }
        return Integer.toString(max + 1);
    }
}
