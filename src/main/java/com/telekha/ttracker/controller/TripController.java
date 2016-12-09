package com.telekha.ttracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telekha.ttracker.dto.TripDto;
import com.telekha.ttracker.dto.TripUpdateDto;
import com.telekha.ttracker.mapper.TripMapper;
import com.telekha.ttracker.model.TripStatus;
import com.telekha.ttracker.service.TripService;

@RestController
@RequestMapping("/api/trip")
public class TripController {

	@Autowired
	private TripService tripService;
	
	@Autowired
	private TripMapper tripMapper;
	
	@RequestMapping(path="/start/{routeId}", method=RequestMethod.POST)
	public TripDto startTrip(@PathVariable Long routeId) {
		return tripMapper.toTripDto(tripService.startTrip(routeId));
	}
	
	@RequestMapping(path="/{tripId}/end/{tripStatus}", method=RequestMethod.POST)
	public TripDto endTrip(@PathVariable Long tripId, @PathVariable TripStatus tripStatus) {
		return tripMapper.toTripDto(tripService.endTrip(tripId, tripStatus));
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public TripDto updateTrip(@RequestBody @Valid TripUpdateDto tripUpdateDto) {
		return tripMapper.toTripDto(tripService.updateTripPosition(tripUpdateDto.getI(), tripUpdateDto.getA(), tripUpdateDto.getO(), tripUpdateDto.getS()));
	}
	
	@RequestMapping(path="/{tripId}", method=RequestMethod.GET)
	public TripDto getTrip(@PathVariable Long tripId) {
		return tripMapper.toTripDto(tripService.getTrip(tripId));
	}
	
	@RequestMapping(path="/livetrips/{routeId}", method=RequestMethod.GET)
	public List<TripDto> getLiveTrips(@PathVariable Long routeId) {
		return tripService.getLiveTrips(routeId).stream()
				.map(trip -> tripMapper.toTripDto(trip))
				.collect(Collectors.toList());
	}
}
