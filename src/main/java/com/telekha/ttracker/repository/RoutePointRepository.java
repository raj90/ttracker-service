package com.telekha.ttracker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.telekha.ttracker.model.Route;
import com.telekha.ttracker.model.RoutePoint;

public interface RoutePointRepository extends CrudRepository<RoutePoint, Long>  {

	List<RoutePoint> findByRoute(Route route);
}
