package com.googlecode.hdbc.dbmigrate.commandline;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.googlecode.hdbc.dbmigrate.io.IDatabaseProvider;
import com.googlecode.hdbc.dbmigrate.io.IFileProvider;

public class CommandLineParser {
	private IDatabaseProvider db;
	private IFileProvider fs;
	private static final Pattern argPtrn = Pattern.compile("^-t(\\d*)$");
	private static final Pattern filePtrn = Pattern.compile("^(\\d{5})-do_.+");
	
	public CommandLineParser(IDatabaseProvider database, IFileProvider fileSystem) {
		db = database;
		fs = fileSystem;
	}

	public RunMode parse(String[] args) {
		if (!containsToVersionParameter(args)) {
			return new InteractiveMode();
		} else {
			Integer requested = requestedVersion(args);
			int max = maxAvailableVersion();
			int current = db.getCurrentVersion();
			if (requested == null || requested > max) {
				requested = max;
			}
			return new AutomatedMode(current, requested);
		}
	}
	
	private Integer requestedVersion(String[] args) {
		for (String arg : args) {
			Matcher m = argPtrn.matcher(arg);
			if (m.matches()) {
				String temp = m.group(1);
				if (temp == null || temp.length() == 0) {
					return null;
				} else {
					return Integer.parseInt(temp);
				}
			}
		}
		throw new IllegalStateException("unable to find the requested version");
	}
	
	private int maxAvailableVersion() {
		String[] files = fs.migrationFileList();
		int max = 0;
		for (String file : files) {
			Matcher m = filePtrn.matcher(file);
			if (m.matches()) {
				String temp = m.group(1);
				int version = Integer.parseInt(temp);
				if (version > max) {
					max = version;
				}
			} else {
				throw new IllegalStateException("regular expression matching error");
			}
		}
		if (max > 0) {
			return max;
		} else {
			throw new IllegalStateException("unable to find file versions to migrate to");
		}
	}
	
	private boolean containsToVersionParameter(String[] args) {
		boolean found = false;
		for (String arg : args) {
			Matcher m = argPtrn.matcher(arg);
			if (m.matches()) {
				found = true;
				break;
			}
		}
		return found;
	}
}
