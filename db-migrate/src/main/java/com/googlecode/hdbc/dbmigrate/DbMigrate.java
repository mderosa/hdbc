package com.googlecode.hdbc.dbmigrate;

import static com.googlecode.hdbc.dbmigrate.Menu.menu;
import static com.googlecode.hdbc.dbmigrate.Menu.processWith;
import static com.googlecode.hdbc.dbmigrate.Menu.validateWith;
import static com.googlecode.hdbc.dbmigrate.MenuItem.item;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EnumMap;

import com.googlecode.hdbc.dbmigrate.commandline.AutomatedMode;
import com.googlecode.hdbc.dbmigrate.commandline.CommandLineParser;
import com.googlecode.hdbc.dbmigrate.commandline.InteractiveMode;
import com.googlecode.hdbc.dbmigrate.commandline.RunMode;
import com.googlecode.hdbc.dbmigrate.io.DatabaseProvider;
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

    /**
     * The entry point to the program
     * <p>
     * The program can be run in interactive mode, or automated mode.  In the interactive
     * mode the user will be presented with a guided menu of selections which will enable
     * him to either generate a do/undo script or a full migration script.  In automated
     * mode the user can generate a full migration script by adding command line parameters.
     * An example is: db-migrate.jar -t10
     * where '-t' is shorthand for 'to' and the number following the '-t' specifies the 
     * version to migrate the database to.  It is also possible to just enter a '-t'.  In
     * this case the database is migrated to the highest version number available in the
     * do directory. 
     */
    public static void main(final String[] args) {
        IFileProvider fs = new FileSystemFileProvider();
        try {
            new DbMigrate().personalizeOnFirstRun(fs);
            RunMode mode = new CommandLineParser(
            		new DatabaseProvider(fs.readProperties()), fs).parse(args);
            if (mode instanceof AutomatedMode) {
            	DbMigrate.runInMode((AutomatedMode) mode, new GoToDbVersionProcessor());
            } else {
            	DbMigrate.runInMode((InteractiveMode) mode);
            }
            System.out.println("Success");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }

    }
    
    protected static void runInMode(InteractiveMode mode) throws IOException {
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
    	
    	menu.run(new EnumMap<Key, String>(Key.class));
    }
    
    protected static void runInMode(AutomatedMode mode, GoToDbVersionProcessor processor) throws IOException {
    	String toVersion;
    	EnumMap<Key, String> params = new EnumMap<Key, String>(Key.class);
    	if (mode.getToVersion() > mode.getFromVersion()) {
    		toVersion = Integer.toString(mode.getToVersion());
    		params.put(Key.CURRENT_DB_VERSION, Integer.toString(mode.getFromVersion()));
    	} else {
    		toVersion = Integer.toString(mode.getFromVersion());
    		params.put(Key.CURRENT_DB_VERSION, Integer.toString(mode.getToVersion()));
    	}
    	processor.process(toVersion, null, params);
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
