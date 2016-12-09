package com.telekha.ttracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.telekha.ttracker.dto.RouteDriverDto;
import com.telekha.ttracker.dto.RouteDto;
import com.telekha.ttracker.model.Route;

@Mapper(componentModel = "spring", uses={RoutePointMapper.class})
public interface RouteMapper {

	@Mappings({
	@Mapping(source = "route.organization.id", target = "organizationId"),
	@Mapping(source = "route.driver.id", target = "driverId")})
	RouteDto toRouteDto(Route route); 
	Route toRoute(RouteDto routeDto);
	@Mappings({
		@Mapping(source = "route.organization.id", target = "organizationId"),
		@Mapping(source = "route.driver.id", target = "driverId"),
		@Mapping(source = "route.driver.name", target = "driverName"),
		@Mapping(source = "route.driver.mobileNo", target = "driverMobileNo")})
	RouteDriverDto toRouteDriverDto(Route route);
	
}
