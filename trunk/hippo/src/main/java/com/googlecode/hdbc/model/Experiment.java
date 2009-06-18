package com.googlecode.hdbc.model;

public class Experiment implements IExperiment {
	private Long uid;
	private String name;
	private String purpose;
	
	public Experiment(Long id, String nm, String purpse) {
		uid = id;
		name = nm;
		purpose = purpse;
	}

	public Long getUid() {
		return uid;
	}
	
	public String getName() {
		return name;
	}

	public String getPurpose() {
		return purpose;
	}
	
}
