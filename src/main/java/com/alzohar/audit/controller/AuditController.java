package com.alzohar.audit.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.alzohar.audit.entity.AuditEntity;
import com.alzohar.audit.repository.AuditRepository;

@RestController
public class AuditController {

	@Autowired
	public AuditRepository repository;

	@GetMapping("/audits")
//	@PreAuthorize("hasAnyAuthority('VENDOR', 'ADMIN')")
	public List<AuditEntity> getAll() {
		List<AuditEntity> list = repository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	@GetMapping("/audits/{id}")
	public Optional<AuditEntity> getAuditById(@PathVariable("id") long id) {
		Optional<AuditEntity> audtitService = repository.findById(id);
		if (audtitService.isEmpty()) {
			return null;
		}
		return audtitService;
	}

//	@GetMapping("/audits/{createdDate}")
//	public Optional<AuditService> getAuditBycreatedAt(@PathVariable("icreatedDate") Date createdDate) {
//		Optional<AuditService> audtitService = Optional.of(repository.getByDate(createdDate));
//		if (audtitService.isEmpty()) {
//			return null;
//		}
//		return audtitService;
//	}

}
