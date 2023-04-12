package com.example.fooddeliveryadmin.service.implementations;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.fooddeliveryadmin.dto.AdminDto;
import com.example.fooddeliveryadmin.exceptions.DuplicateAdminPresent;
import com.example.fooddeliveryadmin.exceptions.InvalidAdminException;
import com.example.fooddeliveryadmin.models.Admin;
import com.example.fooddeliveryadmin.repository.IAdminRepository;
import com.example.fooddeliveryadmin.service.IAdminService;
import com.example.fooddeliveryadmin.util.convertor.AdminConvertor;

@Service
public class AdminServiceImpl implements IAdminService {

	private IAdminRepository adminRepository;

	private AdminConvertor adminConvertor;

	@Autowired
	private AdminServiceImpl(IAdminRepository adminRepository, AdminConvertor adminConvertor) {
		super();
		this.adminRepository = adminRepository;
		this.adminConvertor = adminConvertor;
	}

	@Override
	public Admin register(AdminDto adminDto) {

		Admin admin = adminConvertor.convert(adminDto);
		Optional<Admin> optionalAdmin = adminRepository.findByUsername(admin.getUsername());
		if (optionalAdmin.isPresent())
			throw new DuplicateAdminPresent("Duplicate admin present.");
		
		return adminRepository.save(admin);
	}

	@Override
	public Admin login(AdminDto adminDto) {

		Admin admin = adminConvertor.convert(adminDto);
		Example<Admin> exampleAdmin = Example.of(admin);
		Optional<Admin> optionalAdmin = adminRepository.findOne(exampleAdmin);
		if (optionalAdmin.isEmpty())
			throw new InvalidAdminException("Invalid Admin credentials");
		return optionalAdmin.get();
	}

	@Override
	public Optional<Admin> findAdminByUsername(String username) {

		return Optional.ofNullable(adminRepository.findByUsername(username)
				.orElseThrow(() -> new InvalidAdminException("Admin not found")));
	}

}
