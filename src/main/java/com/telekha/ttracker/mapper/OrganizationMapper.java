package com.telekha.ttracker.mapper;

import org.mapstruct.Mapper;

import com.telekha.ttracker.dto.OrganizationDto;
import com.telekha.ttracker.model.Organization;

@Mapper(componentModel = "spring")
public interface OrganizationMapper {

	OrganizationDto toOrganizationDto(Organization organization);
	Organization toOrganization(OrganizationDto organizationDto);
}
