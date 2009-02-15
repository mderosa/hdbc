
-Building From Source
0. download the source from googlecode
1. from  the code base directory type 'mvn package'
2. from the code base directory type 'mvn assembly:assembly'

-Using
*Take the tar.gz distribution and unpack it to location on you hard disk, DBMIGRATE_HOME.
*In DBMIGRATE_HOME there is a script dbmigrate.bat which runs the program, bringing up
a command line menu.
*Selection 1 in the menu allows one to create new migration scripts.  For each file
name that one gives to the program, the program will create two migration scripts, one
in the DBMIGRATE_HOME/do directory and one in the DBMIGRATE_HOME/undo directory.  One 
should modify these scripts after generation to contain the detailed do and undo actions
one needs.
*Selection 2 in the menu allows one to create a master migration script that a developer
or dba can run from within sqlplus to do all the updates or rollbacks in one fell swoop.  
The update script is called do_migration.sql and the rollback script is called 
undo_migration.sql.  Both are created in the DBMIGRATE_HOME directory.
*After one has created a master migration script, there is a ant build file in the 
DBMIGRATE_HOME directory, called build.xml, that can be used to create a migration 
package suitable for sending to someone else.  To create the migration package just type
'ant' from DBMIGRATE_HOME and the migration package will be created in the
DBMIGRATE_HOME/target directory.

