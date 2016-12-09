package com.telekha.ttracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.telekha.ttracker.model.TripTracker;

public interface TripTrackerRepository extends CrudRepository<TripTracker, Long>  {

}
