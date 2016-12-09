package com.telekha.ttracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.telekha.ttracker.dto.RoutePointDto;
import com.telekha.ttracker.model.RoutePoint;

@Mapper(componentModel = "spring")
public interface RoutePointMapper {

	@Mapping(source = "routePoint.route.id", target = "routeId")
	RoutePointDto toRoutePointDto(RoutePoint routePoint);
	RoutePoint toRoutePoint(RoutePointDto routePointDto);
}
