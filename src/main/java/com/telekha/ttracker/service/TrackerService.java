package com.telekha.ttracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telekha.ttracker.model.Tracker;
import com.telekha.ttracker.repository.TrackerRepository;

@Service
public class TrackerService {

	@Autowired
	private TrackerRepository trackerRepository;
	
	public Tracker findbyMobileNo(String mobileNo) {
		return trackerRepository.findByMobileNo(mobileNo);
	}
}
