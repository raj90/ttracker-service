package com.telekha.ttracker.repository;

import org.springframework.data.repository.CrudRepository;

import com.telekha.ttracker.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByMobileNo(String mobileNo);
}
