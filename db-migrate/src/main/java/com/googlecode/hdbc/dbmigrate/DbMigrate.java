package com.googlecode.hdbc.dbmigrate;

import static com.googlecode.hdbc.dbmigrate.Menu.*;
import static com.googlecode.hdbc.dbmigrate.MenuItem.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumMap;
import com.googlecode.hdbc.dbmigrate.io.FileSystemFileProvider;
import com.googlecode.hdbc.dbmigrate.io.IFileProvider;
import com.googlecode.hdbc.dbmigrate.processor.CurrentDbVersionProcessor;
import com.googlecode.hdbc.dbmigrate.processor.GoToDbVersionProcessor;
import com.googlecode.hdbc.dbmigrate.processor.MigrationScriptNameProcessor;
import com.googlecode.hdbc.dbmigrate.processor.ScriptTypeProcessor;
import com.googlecode.hdbc.dbmigrate.processor.TopLevelMenuResponseProcessor;
import com.googlecode.hdbc.dbmigrate.validator.CurrentDbVersionValidator;
import com.googlecode.hdbc.dbmigrate.validator.GoToDbVersionValidator;
import com.googlecode.hdbc.dbmigrate.validator.MigrationScriptNameValidator;
import com.googlecode.hdbc.dbmigrate.validator.ScriptTypeValidator;
import com.googlecode.hdbc.dbmigrate.validator.TopLevelMenuResponseValidator;

public final class DbMigrate {

    private DbMigrate() {

    }

    public static void main(final String[] args) {
        Menu menu = menu(processWith(TopLevelMenuResponseProcessor.class), validateWith(TopLevelMenuResponseValidator.class), Prompt.ONE_TWO_THREE)
                .add(item("1. Create a new do/undo script from a template",
                        menu(processWith(MigrationScriptNameProcessor.class), validateWith(MigrationScriptNameValidator.class), Prompt.ALPHANUMERIC)
                        .add(item("Enter the name of the migration script:",
                                menu(processWith(ScriptTypeProcessor.class), validateWith(ScriptTypeValidator.class), Prompt.DDL_DML)
                                .add(item("Do you wish to create a DDL script or a PL/SQL DML script (DDL/DML):", null))))))
                .add(item("2. Create a full migration script",
                        menu(processWith(CurrentDbVersionProcessor.class), validateWith(CurrentDbVersionValidator.class), Prompt.NUMERIC)
                        .add(item("Enter the current database version (Enter 0 if the db is currently unversioned):",
                                menu(processWith(GoToDbVersionProcessor.class), validateWith(GoToDbVersionValidator.class), Prompt.NUMERIC)
                                .add(item("Enter the version that you wish to migrate to:", null))))))
                .add(item("3. Quit", null));
        try {
            new DbMigrate().personalizeOnFirstRun(new FileSystemFileProvider());
            menu.run(new EnumMap<Key, String>(Key.class));
            System.out.println("Success");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    protected void personalizeOnFirstRun(final IFileProvider provider) throws IOException {
        File doDir = new File("do");
        if (!doDir.exists()) {
            System.out.println("Configuring project for the target db schema:");
            System.out.println("");
            System.out.println("What is the name of the schema that you will be running scripts against?");
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String response = in.readLine();
            if (response != null && response.length() > 0) {
            provider.initializeDoDirectory(response.trim());
            provider.initializeUnDoDirectory(response.trim());
            provider.initializeTemplatesDirectory(response.trim());
            System.out.println("Initialized...");
            } else {
                System.out.println("Invalid response");
                personalizeOnFirstRun(provider);
            }
        }
    }

}
