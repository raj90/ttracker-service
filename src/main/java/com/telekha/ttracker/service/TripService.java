package com.telekha.ttracker.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telekha.ttracker.model.Route;
import com.telekha.ttracker.model.Subscribe;
import com.telekha.ttracker.model.Trip;
import com.telekha.ttracker.model.TripStatus;
import com.telekha.ttracker.model.TripTracker;
import com.telekha.ttracker.model.TripTrackerNotifStatus;
import com.telekha.ttracker.repository.RouteRepository;
import com.telekha.ttracker.repository.TripRepository;
import com.telekha.ttracker.repository.TripTrackerRepository;
import com.telekha.ttracker.util.GeoUtil;

@Service
public class TripService {

	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private TripRepository tripRepository;
	
	@Autowired
	private TripTrackerRepository tripTrackerRepository;
	
	@Autowired
	private GeoUtil geoUtil;
	
	@Secured("ROLE_DRIVER")
	@Transactional
	public Trip startTrip(Long routeId) {
		Route route = routeRepository.findOne(routeId);
		Trip trip = new Trip();
		trip.setRoute(route);
		trip.setStartTS(new Date());
		trip.setTripStatus(TripStatus.STARTED);
		trip = tripRepository.save(trip);
		return trip;
	}
	
	@Secured("ROLE_DRIVER")
	@Transactional
	public Trip endTrip(Long tripId, TripStatus tripStatus) {
		Trip trip = tripRepository.findOne(tripId);
		trip.setEndTS(new Date());
		trip.setTripStatus(tripStatus);
		trip = tripRepository.save(trip);
		return trip;
	}
	
	@Secured("ROLE_DRIVER")
	@Transactional
	public Trip updateTripPosition(Long tripId, Double latitude, Double longitude, Long seq) {
		Trip trip = tripRepository.findOne(tripId);
		if(trip.getLastSeq() != null && seq <= trip.getLastSeq()) {
			return trip;
		}
		Set<Subscribe> subscriptions = trip.getRoute().getSubscriptions();
		Set<TripTracker> trackers = trip.getTripTrackers();
		List<Subscribe> notifiedSubscriptions = trackers.stream().map(tracker -> tracker.getSubscribe()).collect(Collectors.toList());
		List<Subscribe> unNotifiedSubscriptions = subscriptions.stream().filter(subscribe -> !notifiedSubscriptions.contains(subscribe)).collect(Collectors.toList());
		
		List<TripTracker> tripTrackers = new ArrayList<TripTracker>();
		for(Subscribe subscribe : unNotifiedSubscriptions) {
			if(subscribe.getLatitude() != null && subscribe.getLongitude() != null && geoUtil.isWithinRange(latitude, longitude, subscribe.getLatitude(), subscribe.getLongitude(), subscribe.getNotifKm() == null ? 1 : subscribe.getNotifKm())) {
				TripTracker tripTracker = new TripTracker();
				tripTracker.setLatitude(latitude);
				tripTracker.setLongitude(longitude);
				tripTracker.setNotifTS(new Date());
				tripTracker.setSubscribe(subscribe);
				tripTracker.setTrip(trip);
				tripTracker.setTripTrackerNotifStatus(TripTrackerNotifStatus.INIT);
				tripTrackers.add(tripTracker);
			}
		}
		
		tripTrackerRepository.save(tripTrackers);
		trip.setLastSeq(seq);
		trip.setLastLat(latitude);
		trip.setLastLong(longitude);
		trip.setTripStatus(TripStatus.RUNNING);
		trip = tripRepository.save(trip);
		return trip;
	}
	
	
	public Trip getTrip(Long tripId) {
		Trip trip = tripRepository.findOne(tripId);
		return trip;
	}
	
	public List<Trip> getLiveTrips(Long routeId) {
		Route route = routeRepository.findOne(routeId);
		return tripRepository.findByRouteAndTripStatus(route, TripStatus.RUNNING);
	}
}
