package com.googlecode.hdbc.dbmigrate.commandline;

public abstract class RunMode {
	
	public abstract int getFromVersion();
	public abstract int getToVersion();

}
