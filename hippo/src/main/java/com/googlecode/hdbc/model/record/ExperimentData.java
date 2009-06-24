package com.googlecode.hdbc.model.record;

import org.apache.commons.lang.StringUtils;

public class ExperimentData {
	private Long uid;
	private String title;
	private String purpose;
	private String method;
	private String conclusion;
	
	public ExperimentData() { }
	
	public ExperimentData(Long id, String nm, String purpse) {
		uid = id;
		title = nm;
		purpose = purpse;
	}

	public Long getUid() {
		return uid;
	}
	public void setUid(Long uid) {
		this.uid = uid;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String name) {
		this.title = StringUtils.trimToNull(name);
	}

	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = StringUtils.trimToNull(purpose);
	}

	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = StringUtils.trimToNull(method);
	}

	public String getConclusion() {
		return conclusion;
	}
	public void setConclusion(String conclusion) {
		this.conclusion = StringUtils.trimToNull(conclusion);
	}
	
}