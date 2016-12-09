package com.telekha.ttracker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.telekha.ttracker.model.Route;
import com.telekha.ttracker.model.Trip;
import com.telekha.ttracker.model.TripStatus;

public interface TripRepository  extends CrudRepository<Trip, Long>   {

	List<Trip> findByRouteAndTripStatus(Route route, TripStatus tripStatus);
}
