package com.telekha.ttracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.telekha.ttracker.dto.TrackerDto;
import com.telekha.ttracker.model.Tracker;

@Mapper(componentModel = "spring")
public interface TrackerMapper {

	@Mapping(target="password", ignore=true)
	TrackerDto toTrackerDto(Tracker tracker);
	Tracker toTracker(TrackerDto trackerDto);
	
}
