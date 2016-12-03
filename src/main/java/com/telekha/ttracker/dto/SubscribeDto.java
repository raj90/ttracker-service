package com.telekha.ttracker.dto;

public class SubscribeDto extends BaseDto  {

	private Long id;
	private TrackerDto tracker;
	private Long routeId;
	private String location;
	private Double latitude;
	private Double longitude;
	private Integer notifKm;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public TrackerDto getTracker() {
		return tracker;
	}
	public void setTracker(TrackerDto tracker) {
		this.tracker = tracker;
	}
	public Long getRouteId() {
		return routeId;
	}
	public void setRouteId(Long routeId) {
		this.routeId = routeId;
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
