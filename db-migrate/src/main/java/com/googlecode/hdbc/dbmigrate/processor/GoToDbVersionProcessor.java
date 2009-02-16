    package com.googlecode.hdbc.dbmigrate.processor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import com.googlecode.hdbc.dbmigrate.Key;
import com.googlecode.hdbc.dbmigrate.MenuItem;
import com.googlecode.hdbc.dbmigrate.io.FileSystemFileProvider;
import com.googlecode.hdbc.dbmigrate.io.IFileProvider;

public class GoToDbVersionProcessor implements IInputProcessor {
    private IFileProvider provider;

    public GoToDbVersionProcessor() {
        provider = new FileSystemFileProvider();
    }

    public GoToDbVersionProcessor(final IFileProvider pvdr) {
        provider = pvdr;
    }

    public final void process(final String input, final List<MenuItem> items,
            final EnumMap<Key, String> params) throws IOException {
        int fromVersion = Integer.parseInt(params.get(Key.CURRENT_DB_VERSION));
        int toVersion = Integer.parseInt(input);

        String[] allMigrations = provider.migrationFileList();
        List<String> migrations = this.filteredFileList(allMigrations, fromVersion, toVersion);
        migrations = this.sortedFileList(migrations);
        provider.writeMasterMigrationDoScript(migrations);
        provider.writeMasterMigrationUnDoScript(migrations);
    }

    protected final List<String> filteredFileList(final String[] fileNames, final int from, final int to) {
        List<String> filtered = new ArrayList<String>();
        for (String name : fileNames) {
            int version = fileVersion(name);
            if (version >= from && version <= to) {
                filtered.add(name);
            }
        }
        return filtered;
    }

    protected final List<String> sortedFileList(final List<String> fileNames) {
        List<String> list = new ArrayList<String>();
        for (String name : fileNames) {
            list.add(name);
        }
        Collections.sort(list, new Comparator<String>() {

            public final int compare(final String fst, final String snd) {
                int n1 = fileVersion(fst);
                int n2 = fileVersion(snd);
                return n1 - n2;
            }
        });
        return list;
    }

    private int fileVersion(final String fst) {
        int fstPos = fst.indexOf("-do");
        String fstNum = fst.substring(0, fstPos);
        int n1 = Integer.parseInt(fstNum);
        return n1;
    }

}
