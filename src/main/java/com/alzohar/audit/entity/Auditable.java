package com.alzohar.audit.entity;

import java.util.Date;

import javax.persistence.Temporal;
import static javax.persistence.TemporalType.TIMESTAMP;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Auditable<U> {

	@CreatedBy
	private U createdBy;

	@CreatedDate
	@Temporal(TIMESTAMP)
	private Date createdDate;

	@LastModifiedBy
	private U lastModifiedBy;

	@LastModifiedDate
	@Temporal(TIMESTAMP)
	private Date lastModifiedDate;

}
