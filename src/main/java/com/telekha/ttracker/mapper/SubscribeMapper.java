package com.telekha.ttracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.telekha.ttracker.dto.SubscribeDto;
import com.telekha.ttracker.dto.TrackerDto;
import com.telekha.ttracker.model.Subscribe;
import com.telekha.ttracker.model.Tracker;

@Mapper(componentModel = "spring")
public interface SubscribeMapper {

	@Mappings({
		@Mapping(source = "subscribe.route.id", target = "routeId")})
	SubscribeDto toSubscribeDto(Subscribe subscribe);
	Subscribe toSubscribe(SubscribeDto subscribeDto);
	
	@Mapping(target="password", ignore=true)
	TrackerDto toTrackerDto(Tracker tracker);
	Tracker toTracker(TrackerDto trackerDto);
}
