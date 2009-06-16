package com.googlecode.hdbc.model;

public class Experiment {
	private String name;
	private String purpose;
	
	public Experiment(String nm, String purpse) {
		name = nm;
		purpose = purpse;
	}

	public String getName() {
		return name;
	}

	public String getPurpose() {
		return purpose;
	}
	
	public int level() {
		return 2;
	}
}
