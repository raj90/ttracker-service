package com.telekha.ttracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.telekha.ttracker.dto.AdminDto;
import com.telekha.ttracker.model.Admin;

@Mapper(componentModel = "spring")
public interface AdminMapper {
	
	@Mappings({
	@Mapping(source = "admin.organization.id", target = "organizationId"),
	@Mapping(target="password", ignore=true)})
	AdminDto toAdminDto(Admin admin);
	Admin toAdmin(AdminDto adminDto);
}
