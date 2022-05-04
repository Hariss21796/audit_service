package com.alzohar.audit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "orderservice_table")
public class Order extends Auditable<String> {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "orderId_generator")
	@SequenceGenerator(name = "orderId_generator", initialValue = 1, allocationSize = 1, sequenceName = "orderId_seq")
	private int id;

	@Column(name = "order_name")
	private String orderName;

	@Column(name = "price")
	private double price;

	@Column(name = "description")
	private String desc;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private double phone;

}
