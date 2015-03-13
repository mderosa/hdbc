# Introduction #

I've worked in too many places where it never occurred to the developers that versioning and managing the database systematically is a fundamental prerequisite for managing a product.  So instead of reinventing one of these everywhere I go, here is one I hope to keep and improve for the long term.

# Installation #
  * Download the tar.gz distribution and unpack to a directory on your computer
  * Execute dbmigrate.sh (linux) or dbmigrate.bat (windows).  You will be asked for the name of the schema that this project will manage updates for.  Enter the schema name at the prompt.
  * Once a schema name is provided the software will self initialize and then present the user with the standard interactive menu.  At this point one can enter '3' to exit back to the command line.

# Using in Interactive Mode #
  * Take the tar.gz distribution and unpack it to location on you hard disk, DBMIGRATE\_HOME.
  * In DBMIGRATE\_HOME there is a script dbmigrate.bat which runs the program, bringing up a command line menu.
  * Selection 1 in the menu allows one to create new migration scripts.  For each file name that one inputs, the program will create two migration scripts, one in the DBMIGRATE\_HOME/do directory and one in the DBMIGRATE\_HOME/undo directory.  One should modify these scripts after generation to contain the detailed do and undo actions one needs.
  * Selection 2 in the menu allows one to create a master migration script that a developer or dba can run from within sqlplus to do all the updates or rollbacks in one fell swoop.  The update script is called do\_migration.sql and the rollback script is called undo\_migration.sql.  Both are created in the DBMIGRATE\_HOME directory.
  * After one has created a master migration script, there is a ant build file, DBMIGRATE\_HOME/build.xml, that can be used to create a migration package suitable for sending to someone else.  To create the migration package just type 'ant' from DBMIGRATE\_HOME and the migration package will be created in the DBMIGRATE\_HOME/target directory.

# Using in Automated Mode #
  * Master migration scripts can also be created by running dbmigrate from the command line.  An example usage that would create a master migration script that would bring the database to version 24 is:
```
java -jar ./lib/db-migrate-${project.version}.jar -t24
```
  * Here the -t stands for 'to' and the 24 represents the version to migrate to.  The number after -t is optional.  When this version designation is not provided the program will generate a master migration script which updates the database to the latest version script in the /do directory

# The basics of managing the database #
  * Have a version table that represents the update state of the database - a single number in the table will do
  * Every change to the database increments or decrements the version and logs who ran the changes and when
  * Any single or multiple version increment script has a corresponding decrement script
  * Automate everything so there are no mistakes and everything is repeatable

# This implementation #
  * This tool creates a series of scripts in matched directories like this:
```
DBMIGRATE_HOME
            |__do
                |__00001-do_script1.sql
                |__00002-do_script2.sql
                |__ .....    
            |__undo
                |__00001-undo_script1.sql
                |__00002-undo_script2.sql
                |__ .....
```
  * As developers need to make changes to the database they use this tool to generate matching template scripts in the do / undo directories.  These scripts will be named according to the next number increment in the series and will have automatically generated infrastructure code, which maintains correct versioning and proper logging.
  * Once the developer has added his database change code to both templates.  He can use the tool to automatically generate a master migration script which will run the database forward either one or several versions.  At the same time the forward master migration script is generated a corresponding master rollback script will also be generated.
  * Automating the whole process this way creates a process that is easy to follow and also, because it prevents common script based errors, easy to carry out.
