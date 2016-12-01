package com.telekha.ttracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Inheritance
@DiscriminatorColumn(name="USER_TYPE")
public abstract class User {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    protected Long id;
	
	@NotNull
	@Size(min=4,max=20)
	protected String password;
	
	@NotNull
	@Size(min=2,max=50)
	protected String name;
	
	
	@Column(unique=true)
	@NotNull
	@Pattern(regexp="^\\d{10}$")
	protected String mobileNo;
	
	@Size(min=3,max=50)
	protected String emailId;
	
	@NotNull
	protected Role role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@Column(insertable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTS;
	@Column(insertable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTS;
	
	public Date getCreateTS() {
		return createTS;
	}

	public Date getUpdateTS() {
		return updateTS;
	}

	@PrePersist
	public void onCreate() {
		this.createTS = new Date();
	}
	
	@PreUpdate
	public void onUpdate() {
		this.updateTS = new Date();
	}
}
