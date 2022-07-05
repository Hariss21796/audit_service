package com.alzohar.audit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "audit_log_data")
public class AuditEntity extends Auditable<String> {

	@Id
	@Column(name = "audit_id")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "auditId_generator")
	@SequenceGenerator(name = "auditId_generator", initialValue = 1, allocationSize = 1, sequenceName = "auditId_seq")

	@Audited
	private long id;

	@Column(name = "status")
	private String status;

	@Column(name = "action")
	private String action;

	@Column(name = "old_data")
	private String oldData;

	@Column(name = "updated_data")
	private String updatedData;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "user_name")
	private String userName;

}
