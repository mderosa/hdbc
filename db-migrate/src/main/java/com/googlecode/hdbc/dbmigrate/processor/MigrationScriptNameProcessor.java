package com.googlecode.hdbc.dbmigrate.processor;

import java.io.IOException;
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

    public final void process(final String input, final List<MenuItem> items) throws IOException {
        String finalFileName = this.preprocessRawInputName(input);
        String migrationNumber = this.nextFileIndex(provider.migrationFileList());
        MenuItem nextItem = items.get(0);
        nextItem.passParameter(Key.MIGRATION_NAME, finalFileName);
        nextItem.passParameter(Key.MIGRATION_NUMBER, migrationNumber);
        nextItem.runSubMenu();
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
        Pattern pat = Pattern.compile("^([0-9]+)-do[a-zA-Z0-9]+");
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
        return Integer.toString(max);
    }
}
