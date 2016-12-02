package com.telekha.ttracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telekha.ttracker.model.Driver;
import com.telekha.ttracker.model.Organization;
import com.telekha.ttracker.model.Role;
import com.telekha.ttracker.model.Route;
import com.telekha.ttracker.repository.DriverRepository;
import com.telekha.ttracker.repository.OrganizationRepository;
import com.telekha.ttracker.repository.RouteRepository;

@Service
public class RouteService {

	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Secured("ROLE_ADMIN")
	@Transactional
	public Route create(Long organizationId, Route route) {
		Organization organization = organizationRepository.findOne(organizationId);
		route.setOrganization(organization);
		return routeRepository.save(route);
	}
	
	public Route get(Long id) {
		return routeRepository.findOne(id);
	}
	
	@Secured("ROLE_ADMIN")
	@Transactional
	public void delete(Long id) {
		routeRepository.delete(id);
	}
	
	public List<Route> findByOrganization(Long organizationId) {
		Organization organization = organizationRepository.findOne(organizationId);
		return routeRepository.findByOrganization(organization);
	}
	
	@Secured("ROLE_ADMIN")
	@Transactional
	public void updateDriver(Long routeId, Driver driver) {
		Driver existingDriver = driverRepository.findByMobileNo(driver.getMobileNo());
		if(existingDriver==null) {
			driver.setRole(Role.ROLE_DRIVER);
			driver = driverRepository.save(driver);
		}
		else {
			driver = existingDriver;
		}
		Route route = routeRepository.findOne(routeId);
		route.setDriver(driver);
		routeRepository.save(route);
	}
	
	@Secured("ROLE_ADMIN")
	@Transactional
	public void removeDriver(Long routeId) {
		Route route = routeRepository.findOne(routeId);
		route.setDriver(null);
		routeRepository.save(route);
	}
}
