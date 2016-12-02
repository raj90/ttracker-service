package com.telekha.ttracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telekha.ttracker.dto.DriverDto;
import com.telekha.ttracker.dto.RouteDriverDto;
import com.telekha.ttracker.mapper.DriverMapper;
import com.telekha.ttracker.mapper.RouteMapper;
import com.telekha.ttracker.service.RouteService;

@RestController
@RequestMapping("/api/route")
public class RouteController {

	@Autowired
	private RouteService routeService;
	
	@Autowired
	private RouteMapper routeMapper;
	
	@Autowired
	private DriverMapper driverMapper;
	
	@RequestMapping(path="/{id}", method=RequestMethod.GET)
	public RouteDriverDto get(@PathVariable Long id) {
		return routeMapper.toRouteDriverDto(routeService.get(id));
	}
	
	@RequestMapping(path="/{id}", method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		routeService.delete(id);
	}
	
	@RequestMapping(path="/{id}/driver", method=RequestMethod.DELETE)
	public void removeDriver(@PathVariable Long id) {
		routeService.removeDriver(id);
	}
	
	@RequestMapping(path="/{routeId}/driver", method=RequestMethod.POST)
	public void updateDriver(@PathVariable Long routeId, @RequestBody DriverDto driverDto) {
		routeService.updateDriver(routeId, driverMapper.toDriver(driverDto));
	}
	
	
}
