package com.googlecode.hdbc.dbmigrate;

import static com.googlecode.hdbc.dbmigrate.Menu.*;
import static com.googlecode.hdbc.dbmigrate.MenuItem.*;
import com.googlecode.hdbc.dbmigrate.processor.NullProcessor;
import com.googlecode.hdbc.dbmigrate.validator.NullValidator;

public final class DbMigrate {

    private DbMigrate() {

    }

    public static void main(final String[] args) {
        Menu menu = menu(processWith(NullProcessor.class), vaidateWith(NullValidator.class))
                .add(item("1. Create a new do/undo script from a template",
                        menu(processWith(NullProcessor.class), vaidateWith(NullValidator.class))
                        .add(item("Enter the name of the migration script:",
                                menu(processWith(NullProcessor.class), validateWith(NullValidator.class))
                                .add(item("Do you wish to create a DDL script or a PL/SQL DML script (DDL/DML):", null))))))
                .add(item("2. Create a full migration script",
                        menu(processWith(NullProcessor.class), validateWith(NullValidator.class))
                        .add(item("Enter the current database version (Enter 0 if the db is currently unversioned):",
                                menu(processWith(NullProcessor.class), validateWith(NullValidator.class))
                                .add(item("Enter the version that you wish to migrate to:", null))))));
        menu.run();
    }

}
