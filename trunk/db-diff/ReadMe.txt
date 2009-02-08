
-Installing and Running DbDiff
1. Unpack the install *.tar.gz bundle to an install directory. 
2. In the install directories ./bin directory, modify the config.xml file so that it
contains the appropriate login information for the reference db (your known good
database) and your test db (the database that you want to compare to the ref database)
3. After configuration, from the ./bin directory, execute the dbdiff.bat file to run 
the program

-Building DbDiff
1. If one has the project source, the project jar can be created using maven and the 
command 'mvn package'.
2. To package to project use the command 'mvn assembly:assembly'.  This command
will create a *.tar.gz file in the target directory.


