package com.telekha.ttracker.dto;

import java.util.Date;

public abstract class BaseDto {

	protected Date createTS;
	
	protected Date updateTS;

	public Date getCreateTS() {
		return createTS;
	}

	public void setCreateTS(Date createTS) {
		this.createTS = createTS;
	}

	public Date getUpdateTS() {
		return updateTS;
	}

	public void setUpdateTS(Date updateTS) {
		this.updateTS = updateTS;
	}

	
}
