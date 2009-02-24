package com.googlecode.hdbc.dbmigrate.processor;

import java.io.IOException;
import java.util.EnumMap;
import java.util.List;

import com.googlecode.hdbc.dbmigrate.Key;
import com.googlecode.hdbc.dbmigrate.MenuItem;
import com.googlecode.hdbc.dbmigrate.io.FileSystemFileProvider;
import com.googlecode.hdbc.dbmigrate.io.IFileProvider;

public class ScriptTypeProcessor implements IInputProcessor {
    private IFileProvider provider;
    private static final String SCRIPT_EXTENSION = ".sql";
    protected static final String SCRIPT_NAME_RGX = "\\$\\{script_name\\}";
    protected static final String VERSION_RGX = "\\$\\{version_no\\}";
    private static final int SCRIPT_NUMBER_WIDTH = 5;

    public ScriptTypeProcessor() {
        provider = new FileSystemFileProvider();
    }

    public ScriptTypeProcessor(final IFileProvider prvdr) {
        provider = prvdr;
    }

    public final void process(final String input, final List<MenuItem> items,
            final EnumMap<Key, String> params) throws IOException {
        String rawContent = provider.templateContent(input);
        String doFileName = doFileName(params);
        String doFileContent = rawContent.replaceAll(SCRIPT_NAME_RGX, doFileName);
        doFileContent = doFileContent.replaceAll(VERSION_RGX, params.get(Key.MIGRATION_NUMBER));
        provider.writeDoFile(doFileName, doFileContent);

        String undoFileName = undoFileName(params);
        String undoFileContent = rawContent.replaceAll(SCRIPT_NAME_RGX, doFileName);
        String version = params.get(Key.MIGRATION_NUMBER);
        int n = Integer.parseInt(version);
        String previousVersion = String.valueOf(n - 1);
        undoFileContent = undoFileContent.replaceAll(VERSION_RGX, previousVersion);
        provider.writeUndoFile(undoFileName, undoFileContent);
    }

    private String doFileName(final EnumMap<Key, String> params) {
        StringBuilder builder = new StringBuilder();
        String migrationNumber = leftPad(params.get(Key.MIGRATION_NUMBER), SCRIPT_NUMBER_WIDTH);
        builder.append(migrationNumber)
            .append("-do_")
            .append(params.get(Key.MIGRATION_NAME))
            .append(SCRIPT_EXTENSION);
        return builder.toString();

    }

    private String undoFileName(final EnumMap<Key, String> params) {
        StringBuilder builder = new StringBuilder();
        String migrationNumber = leftPad(params.get(Key.MIGRATION_NUMBER), SCRIPT_NUMBER_WIDTH);
        builder.append(migrationNumber)
            .append("-undo_")
            .append(params.get(Key.MIGRATION_NAME))
            .append(SCRIPT_EXTENSION);
        return builder.toString();
    }

    private String leftPad(final String number, final int width) {
    	StringBuilder temp = new StringBuilder(number);
    	while (temp.length() < width) {
    		temp.insert(0, "0");
    	}
    	return temp.toString();
    }
}
