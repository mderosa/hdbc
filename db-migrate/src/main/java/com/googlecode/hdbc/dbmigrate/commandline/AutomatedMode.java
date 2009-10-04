package com.googlecode.hdbc.dbmigrate.commandline;

public class AutomatedMode extends RunMode {
	int from;
	int to;
	
	public AutomatedMode(int nominalFrom, int nominalTo) {
		from = nominalFrom;
		to = nominalTo;
	}

	@Override
	public int getFromVersion() {
		return from;
	}

	@Override
	public int getToVersion() {
		return to;
	}

}
