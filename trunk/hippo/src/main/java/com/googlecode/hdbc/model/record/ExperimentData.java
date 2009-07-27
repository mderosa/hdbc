package com.googlecode.hdbc.model.record;

import org.apache.commons.lang.StringUtils;

public class ExperimentData {
	private Long uid;
	private String title;
	private String purpose;
	private String method;
	private String conclusion;
	
	public ExperimentData() { }
	
	public ExperimentData(final Long id, final String nm, final String purpse) {
		uid = id;
		title = nm;
		purpose = purpse;
	}

	public final Long getUid() {
		return uid;
	}
	public final void setUid(final Long uid) {
		this.uid = uid;
	}

	public final String getTitle() {
		return title;
	}
	public final void setTitle(final String name) {
		this.title = StringUtils.trimToNull(name);
	}

	public final String getPurpose() {
		return purpose;
	}
	public final void setPurpose(final String purpose) {
		this.purpose = StringUtils.trimToNull(purpose);
	}

	public final String getMethod() {
		return method;
	}
	public final void setMethod(final String method) {
		this.method = StringUtils.trimToNull(method);
	}

	public final String getConclusion() {
		return conclusion;
	}
	public final void setConclusion(final String conclusion) {
		this.conclusion = StringUtils.trimToNull(conclusion);
	}
	
}