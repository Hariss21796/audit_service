package com.alzohar.audit.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.alzohar.audit.entity.Order;
import com.alzohar.audit.repository.OrderRepository;

@RestController
public class OrderController {

	@Autowired
	public OrderRepository repository;

	@GetMapping("/orders")
//	@PreAuthorize("hasAnyAuthority('VENDOR', 'ADMIN')")
	public List<Order> getAll() {
		List<Order> list = repository.findAll();
		if (list.isEmpty()) {
			return null;
		}
		return list;
	}

	@GetMapping("/orders/{id}")
	public Optional<Order> getOrderById(@PathVariable("id") int id) {
		Optional<Order> order = repository.findById(id);
		if (order.isEmpty()) {
			return null;
		}
		return order;
	}

	@PostMapping("/orders")
//	@PreAuthorize("hasAuthority('ADMIN')")
	public Order addOrder(@RequestBody Order order) {
		return repository.save(order);
	}

	@PutMapping("/orders")
//	@PreAuthorize("hasAnyAuthority('VENDOR', 'ADMIN')")
	public String updateOrder(@RequestBody Order order, @PathVariable("id") int id) {
		repository.save(order);
		return "Order is updated successfully !";

	}

	@DeleteMapping("/orders/{id}")
	public String deleteOrder(@PathVariable("id") int id) {
		repository.deleteById(id);
		return "Order is deleted successfully !";
	}

}
