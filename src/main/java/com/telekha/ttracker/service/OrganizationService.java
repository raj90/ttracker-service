package com.telekha.ttracker.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telekha.ttracker.model.Admin;
import com.telekha.ttracker.model.Organization;
import com.telekha.ttracker.model.Role;
import com.telekha.ttracker.repository.AdminRepository;
import com.telekha.ttracker.repository.OrganizationRepository;

@Service
public class OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Secured("ROLE_SUPERADMIN")
	@Transactional
	public Organization create(Organization organization) {
		return organizationRepository.save(organization);
	}
	
	public Iterable<Organization> getAll() {
		return organizationRepository.findAll();
	}
	
	public Organization get(Long id) {
		return organizationRepository.findOne(id);
	}
	
	public Set<Admin> getAdmins(Long organizationId) {
		Organization org = organizationRepository.findOne(organizationId);
		return org.getAdmins();
	}
	
	@Secured("ROLE_SUPERADMIN")
	public void delete(Long id) {
		organizationRepository.delete(id);
	}
	
	@Secured("ROLE_SUPERADMIN")
	@Transactional
	public Admin addAdmin(Long organizationId, Admin admin) {
		Organization org = organizationRepository.findOne(organizationId);
		admin.setRole(Role.ROLE_ADMIN);
		admin.setOrganization(org);
		admin = adminRepository.save(admin);
		return admin;
	}
	
	@Secured("ROLE_SUPERADMIN")
	@Transactional
	public void deleteAdmin(Long organizationId, Long adminId) {
		adminRepository.delete(adminId);
	}
	
	
	
}
