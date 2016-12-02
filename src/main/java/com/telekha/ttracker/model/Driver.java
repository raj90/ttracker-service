package com.telekha.ttracker.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("D")
public class Driver extends User{

	@OneToMany(mappedBy="driver")
	private Set<Route> routes = new HashSet<Route>();

	public Set<Route> getRoutes() {
		return routes;
	}

	public void setRoutes(Set<Route> routes) {
		this.routes = routes;
	}
	
	
}
