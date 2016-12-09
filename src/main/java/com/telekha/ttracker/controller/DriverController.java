package com.telekha.ttracker.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telekha.ttracker.dto.DriverDto;
import com.telekha.ttracker.dto.RouteDriverDto;
import com.telekha.ttracker.mapper.DriverMapper;
import com.telekha.ttracker.mapper.RouteMapper;
import com.telekha.ttracker.service.DriverService;
import com.telekha.ttracker.service.RouteService;

@RestController
@RequestMapping("/api/driver")
public class DriverController extends UserController {

	@Autowired
	private DriverService driverService;
	
	@Autowired
	private DriverMapper driverMapper;
	
	@Autowired
	private RouteService routeService;
	
	@Autowired
	private RouteMapper routeMapper;
	
	@RequestMapping(method=RequestMethod.GET)
	public DriverDto get(Principal principal) {
		return driverMapper.toDriverDto(driverService.findByMobileNo(principal.getName()));
	}
	
	@RequestMapping(path="/route",method=RequestMethod.GET)
	public List<RouteDriverDto> getRoute(Principal principal) {
		return routeService.findByDriverMobileNo(principal.getName()).stream()
				.map(route -> routeMapper.toRouteDriverDto(route))
				.collect(Collectors.toList());
	}
}
