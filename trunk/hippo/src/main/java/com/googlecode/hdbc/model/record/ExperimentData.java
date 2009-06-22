package com.googlecode.hdbc.model.record;

public class ExperimentData {
	private long uid;
	private String name;
	private String purpose;
	private String method;
	private String conclusion;
	
	public ExperimentData() { }
	
	public ExperimentData(Long id, String nm, String purpse) {
		uid = id;
		name = nm;
		purpose = purpse;
	}

	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = conclusion;
	}
	
}