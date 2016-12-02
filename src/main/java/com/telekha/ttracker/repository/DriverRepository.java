package com.telekha.ttracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.telekha.ttracker.model.Driver;

public interface DriverRepository extends CrudRepository<Driver, Long> {
	Driver findByMobileNo(String mobileNo);
}
