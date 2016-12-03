package com.telekha.ttracker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.telekha.ttracker.model.Driver;
import com.telekha.ttracker.model.Organization;
import com.telekha.ttracker.model.Route;

public interface RouteRepository extends CrudRepository<Route, Long> {
	List<Route> findByOrganization(Organization organization);
	List<Route> findByDriver(Driver driver);
}
