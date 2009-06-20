package com.googlecode.hdbc.model.record;

public class ExperimentData {
	private Long uid;
	private String name;
	private String purpose;
	
	public ExperimentData(Long id, String nm, String purpse) {
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
