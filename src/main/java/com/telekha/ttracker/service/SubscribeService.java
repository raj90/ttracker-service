package com.telekha.ttracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telekha.ttracker.model.Role;
import com.telekha.ttracker.model.Route;
import com.telekha.ttracker.model.Subscribe;
import com.telekha.ttracker.model.Tracker;
import com.telekha.ttracker.repository.RouteRepository;
import com.telekha.ttracker.repository.SubscribeRepository;
import com.telekha.ttracker.repository.TrackerRepository;

@Service
public class SubscribeService {

	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private SubscribeRepository subscribeRepository;
	
	@Autowired
	private TrackerRepository trackerRepository;
	
	@Secured("ROLE_ADMIN")
	@Transactional
	public Subscribe create(Long routeId, Subscribe subscribe) {
		Route route = routeRepository.findOne(routeId);
		subscribe.setRoute(route);
		Tracker existingTracker = trackerRepository.findByMobileNo(subscribe.getTracker().getMobileNo());
		if(existingTracker == null) {
			subscribe.getTracker().setRole(Role.ROLE_TRACKER);
			subscribe.setTracker(trackerRepository.save(subscribe.getTracker()));
		}
		return subscribeRepository.save(subscribe);
	}
	
	@Secured({"ROLE_ADMIN","ROLE_TRACKER"})
	@Transactional
	public void delete(Long id) {
		subscribeRepository.delete(id);
	}
	
	public Subscribe get(Long id) {
		return subscribeRepository.findOne(id);
	}
	
	public List<Subscribe> findByRouteId(Long routeId) {
		Route route = routeRepository.findOne(routeId);
		return subscribeRepository.findByRoute(route);	
	}
	
	public List<Subscribe> findByTrackerId(Long trackerId) {
		Tracker tracker = trackerRepository.findOne(trackerId);
		return subscribeRepository.findByTracker(tracker);
	}

	public List<Subscribe> findByTrackerMobileNo(String mobileNo) {
		Tracker tracker = trackerRepository.findByMobileNo(mobileNo);
		return subscribeRepository.findByTracker(tracker);
	}
}
