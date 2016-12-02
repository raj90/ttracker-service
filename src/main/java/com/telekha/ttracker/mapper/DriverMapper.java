package com.telekha.ttracker.mapper;

import org.mapstruct.Mapper;

import com.telekha.ttracker.dto.DriverDto;
import com.telekha.ttracker.model.Driver;

@Mapper(componentModel = "spring")
public interface DriverMapper {

	DriverDto toDriverDto(Driver driver);
	Driver toDriver(DriverDto driverDto);
}
