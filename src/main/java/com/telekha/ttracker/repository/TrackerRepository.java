package com.telekha.ttracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.telekha.ttracker.model.Tracker;

public interface TrackerRepository extends CrudRepository<Tracker, Long>  {

	Tracker findByMobileNo(String mobileNo);
}
