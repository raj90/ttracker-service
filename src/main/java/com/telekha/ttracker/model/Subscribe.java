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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(uniqueConstraints={ @UniqueConstraint(columnNames = {"tracker_id", "route_id"}) }) 
public class Subscribe {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
	@NotNull
	private Tracker tracker;
	
	@ManyToOne
	@NotNull
	private Route route;
	
	@Size(min=1,max=50)
	private String location;
	
	private Double latitude;
	
	private Double longitude;
	
	private Integer notifKm;
	
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

	public Tracker getTracker() {
		return tracker;
	}

	public void setTracker(Tracker tracker) {
		this.tracker = tracker;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public Integer getNotifKm() {
		return notifKm;
	}

	public void setNotifKm(Integer notifKm) {
		this.notifKm = notifKm;
	}
	
	
}
