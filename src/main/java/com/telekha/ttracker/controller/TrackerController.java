package com.telekha.ttracker.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telekha.ttracker.dto.SubscribeDto;
import com.telekha.ttracker.dto.TrackerDto;
import com.telekha.ttracker.mapper.SubscribeMapper;
import com.telekha.ttracker.mapper.TrackerMapper;
import com.telekha.ttracker.service.SubscribeService;
import com.telekha.ttracker.service.TrackerService;

@RestController
@RequestMapping("/api/tracker")
public class TrackerController extends UserController {

	@Autowired
	private TrackerService trackerService;
	
	@Autowired
	private TrackerMapper trackerMapper;
	
	@Autowired
	private SubscribeService subscribeService;
	
	@Autowired
	private SubscribeMapper subscribeMapper;
	
	@RequestMapping(method=RequestMethod.GET)
	public TrackerDto get(Principal principal) {
		return trackerMapper.toTrackerDto(trackerService.findbyMobileNo(principal.getName()));
	}
	
	@RequestMapping(path="/route",method=RequestMethod.GET)
	public List<SubscribeDto> getSubscribe(Principal principal) {
		return subscribeService.findByTrackerMobileNo(principal.getName()).stream()
				.map(subscribe -> subscribeMapper.toSubscribeDto(subscribe))
				.collect(Collectors.toList());
	}
}
