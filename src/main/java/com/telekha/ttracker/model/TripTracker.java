package com.telekha.ttracker.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(uniqueConstraints={ @UniqueConstraint(columnNames = {"trip_id", "subscribe_id"}) }) 
public class TripTracker {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
	@NotNull
	private Trip trip;
	
	@ManyToOne
	@NotNull
	private Subscribe subscribe;
	
	@Column(insertable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date notifTS;
	
	private Double latitude;
	private Double longitude;
	
	private TripTrackerNotifStatus tripTrackerNotifStatus;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Trip getTrip() {
		return trip;
	}
	public void setTrip(Trip trip) {
		this.trip = trip;
	}
	public Subscribe getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(Subscribe subscribe) {
		this.subscribe = subscribe;
	}
	public Date getNotifTS() {
		return notifTS;
	}
	public void setNotifTS(Date notifTS) {
		this.notifTS = notifTS;
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
	public TripTrackerNotifStatus getTripTrackerNotifStatus() {
		return tripTrackerNotifStatus;
	}
	public void setTripTrackerNotifStatus(TripTrackerNotifStatus tripTrackerNotifStatus) {
		this.tripTrackerNotifStatus = tripTrackerNotifStatus;
	}
	
	
	
	
}
