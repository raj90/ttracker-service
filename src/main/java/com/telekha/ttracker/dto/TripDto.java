package com.telekha.ttracker.dto;

import java.util.Date;

import com.telekha.ttracker.model.TripStatus;

public class TripDto {

	private Long id;
	private Long routeId;
	private Date startTS;
	private Date endTS;
	private TripStatus tripStatus;
	private Long distanceTravelled;
	private Double lastLat;
	private Double lastLong;
	private Long lastSeq;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getRouteId() {
		return routeId;
	}
	public void setRouteId(Long routeId) {
		this.routeId = routeId;
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
