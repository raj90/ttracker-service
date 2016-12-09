package com.telekha.ttracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.telekha.ttracker.dto.TripDto;
import com.telekha.ttracker.model.Trip;

@Mapper(componentModel = "spring")
public interface TripMapper {

	@Mapping(source = "trip.route.id", target = "routeId")
	TripDto toTripDto(Trip trip);
	Trip toTrip(TripDto tripDto);
}
