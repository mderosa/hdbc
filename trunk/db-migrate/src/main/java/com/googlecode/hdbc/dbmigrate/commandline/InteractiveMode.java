package com.googlecode.hdbc.dbmigrate.commandline;

public class InteractiveMode implements RunMode {

	@Override
	public int getFromVersion() {
		throw new IllegalStateException("this value must be provided by user input");
	}

	@Override
	public int getToVersion() {
		throw new IllegalStateException("this value must be provided by user input");
	}
	
}
