package com.alzohar.audit.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.SerializationUtils;

import lombok.Data;

@Entity
@Data
@Table(name = "product_service_table")
@EntityListeners(CustomAuditEntityListner.class)
public class Product extends Auditable<String> implements Serializable {

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

	@Transient
	private transient Product oldValue;

	@PostLoad
	private void saveState() {
		this.oldValue = (Product) SerializationUtils.clone(this); // from apache commons-lang
	}

//	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	@JoinTable(name = "audit_user_prod", joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//	private User user;

}
