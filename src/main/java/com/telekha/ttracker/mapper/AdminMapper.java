package com.telekha.ttracker.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.telekha.ttracker.dto.AdminDto;
import com.telekha.ttracker.model.Admin;

@Mapper(componentModel = "spring")
public interface AdminMapper {
	
	@Mapping(source = "admin.organization.id", target = "organizationId")
	AdminDto toAdminDto(Admin admin);
	Admin toAdmin(AdminDto adminDto);
}
