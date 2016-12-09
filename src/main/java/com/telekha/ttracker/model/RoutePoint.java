package com.telekha.ttracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

@Entity
public class RoutePoint {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@Size(min=1,max=50)
	private String location;
	
	@Size(min=1,max=50)
	private String landmark;
	
	private Double latitude;
	
	private Double longitude;
	
	@Size(min=1,max=10)
	private String pickupTime;
	
	@Size(min=1,max=10)
	private String dropTime;
	
	@ManyToOne
	private Route route;
	
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLandmark() {
		return landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getPickupTime() {
		return pickupTime;
	}

	public void setPickupTime(String pickupTime) {
		this.pickupTime = pickupTime;
	}

	public String getDropTime() {
		return dropTime;
	}

	public void setDropTime(String dropTime) {
		this.dropTime = dropTime;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public void setCreateTS(Date createTS) {
		this.createTS = createTS;
	}

	public void setUpdateTS(Date updateTS) {
		this.updateTS = updateTS;
	}
	
}
