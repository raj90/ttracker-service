package com.telekha.ttracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telekha.ttracker.dto.DriverDto;
import com.telekha.ttracker.dto.RouteDriverDto;
import com.telekha.ttracker.dto.RoutePointDto;
import com.telekha.ttracker.dto.SubscribeDto;
import com.telekha.ttracker.mapper.DriverMapper;
import com.telekha.ttracker.mapper.RouteMapper;
import com.telekha.ttracker.mapper.RoutePointMapper;
import com.telekha.ttracker.mapper.SubscribeMapper;
import com.telekha.ttracker.service.RouteService;
import com.telekha.ttracker.service.SubscribeService;

@RestController
@RequestMapping("/api/route")
public class RouteController {

	@Autowired
	private RouteService routeService;
	
	@Autowired
	private SubscribeService subscribeService;
	
	@Autowired
	private RouteMapper routeMapper;
	
	@Autowired
	private DriverMapper driverMapper;
	
	@Autowired
	private SubscribeMapper subscribeMapper;
	
	@Autowired
	private RoutePointMapper routePointMapper;
	
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
	
	@RequestMapping(path="/{routeId}/subscribe", method=RequestMethod.POST)
	public SubscribeDto subscribe(@PathVariable Long routeId, @RequestBody SubscribeDto subscribeDto) {
		return subscribeMapper.toSubscribeDto(subscribeService.create(routeId, subscribeMapper.toSubscribe(subscribeDto)));
	}
	
	@RequestMapping(path="/{routeId}/subscribe", method=RequestMethod.GET)
	public List<SubscribeDto> getSubscribes(@PathVariable Long routeId) {
		return subscribeService.findByRouteId(routeId).stream()
				.map(subscribe -> subscribeMapper.toSubscribeDto(subscribe))
				.collect(Collectors.toList());
	}
	
	@RequestMapping(path="/{routeId}/subscribe/{subscribeId}", method=RequestMethod.DELETE)
	public void unsubscribe(@PathVariable Long routeId, @PathVariable Long subscribeId) {
		subscribeService.delete(subscribeId);
	}
	
	@RequestMapping(path="/{routeId}/routepoint", method=RequestMethod.POST)
	public RouteDriverDto addRoutePoint(@PathVariable Long routeId, @RequestBody RoutePointDto routePoint) {
		return routeMapper.toRouteDriverDto(routeService.addRoutePoint(routeId, routePointMapper.toRoutePoint(routePoint)));
	}
	
	@RequestMapping(path="/{routeId}/routepoint/{id}", method=RequestMethod.DELETE)
	public void deleteRoutePoint(@PathVariable Long routeId, @PathVariable Long id) {
		routeService.deleteRoutePoint(routeId, id);
	}
	
	@RequestMapping(path="/{routeId}/routepoint", method=RequestMethod.GET)
	public List<RoutePointDto> getRoutePoint(@PathVariable Long routeId) {
		return routeService.getRoutePoints(routeId).stream()
				.map(routePoint -> routePointMapper.toRoutePointDto(routePoint))
				.collect(Collectors.toList());
	}
	
}
