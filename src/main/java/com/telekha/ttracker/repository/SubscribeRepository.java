package com.telekha.ttracker.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.telekha.ttracker.model.Route;
import com.telekha.ttracker.model.Subscribe;
import com.telekha.ttracker.model.Tracker;

public interface SubscribeRepository extends CrudRepository<Subscribe, Long>  {
	List<Subscribe> findByRoute(Route route);
	List<Subscribe> findByTracker(Tracker tracker);
}
