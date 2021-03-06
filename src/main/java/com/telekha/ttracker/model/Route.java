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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Route {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
	
	@NotNull
	@Size(min=1,max=50)
	private String name;
	
	@Size(min=1,max=50)
	private String location;
	
	private Double latitude;
	
	private Double longitude;
	
	@Size(min=1,max=50)
	private String vehicleNo;
	
	@Size(min=1,max=50)
	private String vehicleName;
	
	@Size(min=1,max=10)
	private String pickupTime;
	
	@Size(min=1,max=10)
	private String dropTime;
	
	@ManyToOne
	private Driver driver;
	
	@NotNull
	@ManyToOne
	private Organization organization;
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="route")
	private Set<Subscribe> subscriptions = new HashSet<Subscribe>();
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="route")
	private Set<RoutePoint> routePoints = new HashSet<RoutePoint>();
	
	@OneToMany(cascade={CascadeType.ALL},mappedBy="route")
	private Set<Trip> trips = new HashSet<Trip>();
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
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

	public Set<Subscribe> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<Subscribe> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Set<RoutePoint> getRoutePoints() {
		return routePoints;
	}

	public void setRoutePoints(Set<RoutePoint> routePoints) {
		this.routePoints = routePoints;
	}

	public Set<Trip> getTrips() {
		return trips;
	}

	public void setTrips(Set<Trip> trips) {
		this.trips = trips;
	}
	
	
	
	
}
