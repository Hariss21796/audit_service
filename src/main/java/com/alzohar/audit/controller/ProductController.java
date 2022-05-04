package com.alzohar.audit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alzohar.audit.entity.AuditEntity;
import com.alzohar.audit.entity.Product;
import com.alzohar.audit.repository.AuditRepository;
import com.alzohar.audit.repository.ProductRepository;

@RestController
public class ProductController {

	@Autowired
	public ProductRepository repository;
	
	@Autowired
	public AuditRepository auditRepository;

	@GetMapping("/products")
//	@PreAuthorize("hasAnyAuthority('VENDOR', 'ADMIN')")
	public List<Product> getAll() {
		List<Product> list = repository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	@GetMapping("/products/{id}")
	public Optional<Product> getProductById(@PathVariable("id") int id) {
		Optional<Product> product = repository.findById(id);
		if (product.isEmpty()) {
			return null;
		}
		return product;
	}

//	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/products")
	public Product addProduct(@RequestBody Product product,AuditEntity audit) {
//		 AuditRepository.saveAudit(audit);
		return repository.save(product);
	}

	@PutMapping("/products/{id}")
//	@PreAuthorize("hasAnyAuthority('VENDOR', 'ADMIN')")
	public String updateProduct(@RequestBody Product product, @PathVariable("id") int id) {
		repository.save(product);
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			return currentUserName;
		}
		return "Product is updated successfully !";

	}

	@DeleteMapping("/products/{id}")
	public String deleteProduct(@PathVariable("id") int id) {
		repository.deleteById(id);
		return "Product is deleted successfully !";
	}

}
