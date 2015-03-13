# Introduction #

Once a team starts shipping real products databases start to multiply.  There are databases for different version support, for different groups, and for individual efforts.  And even with the best efforts differences from the expected version 'standard' creep into those databases.  Sometimes its nice to be able to figure out how out of sync two databases are - or maybe just verify that the source of a problem isn't because two databases are not identical.  This tool tells one what the differences are.

# At the moment this can #
  * Compare oracle databases
  * Compare whether two db schemas have the same set of user objects
  * Compare the details of tables, views, stored procedures, and functions

# Using #
  * Get the distribution and unpackage it to a directory
  * In the bin sub directory modify the config.xml file to point to the databases you want to diff
  * Run dbdiff.bat