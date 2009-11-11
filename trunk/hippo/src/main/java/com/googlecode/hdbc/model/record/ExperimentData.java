package com.googlecode.hdbc.model.record;

import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang.StringUtils;

public class ExperimentData extends HippoObject {
	private Long uid;
	private String title;
	private String purpose;
	private String method;
	private String conclusion;
	private Date modifiedDate = Calendar.getInstance().getTime();
	private Long modifiedBy = 0L;
	private boolean active = true;
	
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

	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date date) {
		this.modifiedDate = date;
	}

	public Long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Long userId) {
		this.modifiedBy = userId;
	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean actv) {
		this.active = actv;
	}

}