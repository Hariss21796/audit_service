package com.alzohar.audit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Data;

@Entity
@Data
@Table(name = "product_service_table")
@EntityListeners(CustomAuditEntityListner.class)
public class Product extends Auditable<String> {

	@Id
	@Column(name = "prod_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "productId_generator")
	@SequenceGenerator(name = "productId_generator", initialValue = 1, allocationSize = 1, sequenceName = "productId_seq")
	private int id;

//	@LastModifiedBy
	@Column(name = "prod_name")
	private String prodName;

	@Column(name = "description")
	private String desc;

	@Column(name = "enabled")
	private boolean enabled;

	@Column(name = "price")
	private double price;


//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinTable(name = "audit_user_prod", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//	private User user;

}
