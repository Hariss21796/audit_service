package com.alzohar.audit.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alzohar.audit.entity.User;
import com.alzohar.audit.repository.UserRepository;
import com.alzohar.audit.service.UserService;

@RestController
public class UserController {

	private static final Logger LOGGER = LogManager.getLogger(UserController.class);

	@Autowired
	UserRepository repository;

	@Autowired
	UserService userService;

	@GetMapping("/user/{id}")
	public Optional<User> getUserById(@PathVariable("id") long id) {
		Optional<User> user = repository.findById(id);
		if (user != null) {
			LOGGER.info("User is found with id :: " + id);
			return user;
		}
		LOGGER.info("User is not found with id " + id);
		throw new RuntimeException("User Not Found by Given Id = " + id);
	}

	@GetMapping("/users")
	// @PreAuthorize("hasAnyAuthority('VENDOR', 'ADMIN')")
	public List<User> getUsers() {
		List<User> list = repository.findAll();
		if (list.isEmpty()) {
			LOGGER.info("User is not found , user list is empty ");
			throw new RuntimeException("User list is empty, Zero records found !");
		}
		LOGGER.info("Get users list is successfull");
		return list;

	}

	@PostMapping("/users")
	// @PreAuthorize("hasAuthority('ADMIN')")
	public User addUser(@RequestBody User user) {
		return repository.save(user);
	}

	@PostMapping("/users/register")
	public User register(@RequestBody User user) {
		return userService.register(user);

	}

	@PutMapping("/users")
	// @PreAuthorize("hasAnyAuthority('VENDOR', 'ADMIN')")
	public User updateUser(@RequestBody User user) {
		return repository.save(user);
	}

	@DeleteMapping("/users/{id}")
	public String deleteUserById(@PathVariable("id") long id) {
		repository.deleteById(id);
		return "User deleted successfully !";
	}

}
