package com.googlecode.hdbc.dbmigrate.commandline;

public interface RunMode {
	
	int getFromVersion();
	int getToVersion();

}
