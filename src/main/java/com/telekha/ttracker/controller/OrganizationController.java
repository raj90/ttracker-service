package com.telekha.ttracker.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.telekha.ttracker.dto.AdminDto;
import com.telekha.ttracker.dto.OrganizationDto;
import com.telekha.ttracker.mapper.AdminMapper;
import com.telekha.ttracker.mapper.OrganizationMapper;
import com.telekha.ttracker.model.Organization;
import com.telekha.ttracker.service.OrganizationService;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private OrganizationMapper organizationMapper;
	
	@RequestMapping(method=RequestMethod.POST)
	public OrganizationDto create(@RequestBody @Valid OrganizationDto organizationDto) {
		Organization organization = organizationMapper.toOrganization(organizationDto);
		organization = organizationService.create(organization);
		organizationDto.setId(organization.getId());
		return organizationDto;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public List<OrganizationDto> getAll() {
		return StreamSupport.stream(organizationService.getAll().spliterator(), false)
		.map(org -> organizationMapper.toOrganizationDto(org))
		.collect(Collectors.toList());
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable Long id) {
		organizationService.delete(id);
	}
	
	@RequestMapping(path="/{id}",method=RequestMethod.GET)
	public OrganizationDto get(@PathVariable Long id) {
		return organizationMapper.toOrganizationDto(organizationService.get(id));
	}
	
	@RequestMapping(path="/{id}/admin",method=RequestMethod.POST)
	public AdminDto addAdmin(@PathVariable Long id, @RequestBody AdminDto  adminDto) {
		return adminMapper.toAdminDto(organizationService.addAdmin(id, adminMapper.toAdmin(adminDto)));
	}
	
	@RequestMapping(path="/{organizationId}/admin/{adminId}",method=RequestMethod.DELETE)
	public void deleteAdmin(@PathVariable Long organizationId, @PathVariable Long adminId) {
		organizationService.deleteAdmin(organizationId, adminId);
	}
	
	@RequestMapping(path="/{organizationId}/admin",method=RequestMethod.GET)
	public List<AdminDto> getAdmins(@PathVariable Long organizationId) {
		return organizationService.getAdmins(organizationId).stream()
		.map(admin -> adminMapper.toAdminDto(admin))
		.collect(Collectors.toList());
	}
}
