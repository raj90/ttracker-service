package com.telekha.ttracker.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class Trip {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@ManyToOne
	private Route route;
	
	@Column(insertable = true, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTS;
	
	@Column(insertable = false, updatable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date endTS;
	
	@NotNull
	private TripStatus tripStatus;
	
	@Column(insertable = false, updatable = true)
	private Long distanceTravelled;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="trip")
	private Set<TripTracker> tripTrackers = new HashSet<TripTracker>();
	
	@Column(insertable = false, updatable = true)
	private Double lastLat;
	@Column(insertable = false, updatable = true)
	private Double lastLong;
	@Column(insertable = false, updatable = true)
	private Long lastSeq;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Date getStartTS() {
		return startTS;
	}

	public void setStartTS(Date startTS) {
		this.startTS = startTS;
	}

	public Date getEndTS() {
		return endTS;
	}

	public void setEndTS(Date endTS) {
		this.endTS = endTS;
	}

	public TripStatus getTripStatus() {
		return tripStatus;
	}

	public void setTripStatus(TripStatus tripStatus) {
		this.tripStatus = tripStatus;
	}

	public Long getDistanceTravelled() {
		return distanceTravelled;
	}

	public void setDistanceTravelled(Long distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}

	public Set<TripTracker> getTripTrackers() {
		return tripTrackers;
	}

	public void setTripTrackers(Set<TripTracker> tripTrackers) {
		this.tripTrackers = tripTrackers;
	}

	public Double getLastLat() {
		return lastLat;
	}

	public void setLastLat(Double lastLat) {
		this.lastLat = lastLat;
	}

	public Double getLastLong() {
		return lastLong;
	}

	public void setLastLong(Double lastLong) {
		this.lastLong = lastLong;
	}

	public Long getLastSeq() {
		return lastSeq;
	}

	public void setLastSeq(Long lastSeq) {
		this.lastSeq = lastSeq;
	}
	
	
	
	
}
