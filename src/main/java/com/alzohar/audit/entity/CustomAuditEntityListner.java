package com.alzohar.audit.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.alzohar.audit.constant.Action;
import com.alzohar.audit.constant.Status;
import com.alzohar.audit.repository.AuditRepository;
import com.alzohar.audit.util.BeanUtil;

import java.util.Date;

@Service
public class CustomAuditEntityListner {

	@PreRemove
	public void preRemove(Object object) {
		AuditRepository auditRepository = BeanUtil.getBean(AuditRepository.class);
		final AuditEntity audit = createAudit(object, Action.DELETED.value(), Status.SUCCESS.value());
		auditRepository.save(audit);
	}

	@PrePersist
	public void prePersist(Object object) {
		AuditRepository auditRepository = BeanUtil.getBean(AuditRepository.class);
		final AuditEntity audit = createAudit(object, Action.INSERTED.value(), Status.SUCCESS.value());
		auditRepository.save(audit);
	}

	@PreUpdate
	public void preUpdate(Object object) {
		AuditRepository auditRepository = BeanUtil.getBean(AuditRepository.class);
		final AuditEntity audit = updateAudit(object, object, Action.UPDATED.value(), Status.SUCCESS.value());
		auditRepository.save(audit);
	}

	private AuditEntity createAudit(Object object, String action, String status) {
		final AuditEntity audit = new AuditEntity();
//		audit.setEntity(object.getClass().getSimpleName());
		audit.setOldData(object.toString());
		audit.setAction(action);
		audit.setStatus(status);
		audit.setUserName(getUsername());
		audit.setCreatedBy(getUsername());
		audit.setCreatedDate(new Date());
		return audit;
	}

	private AuditEntity updateAudit(Object oldObj, Object newObj, String action, String status) {
		final AuditEntity audit = new AuditEntity();
		audit.setAction(action);
		audit.setOldData(oldObj.toString());
		audit.setUpdatedData(newObj.toString());
		audit.setStatus(status);
		audit.setUserName(getUsername());
		audit.setCreatedBy(getUsername());
		audit.setCreatedDate(new Date());
		return audit;
	}

	public String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			return currentUserName;
		}
		return null;
	}

//	public long getId() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (!(authentication instanceof AnonymousAuthenticationToken)) {
//			long currentUserid = ((AuditEntity) authentication).getId();
//			return currentUserid;
//		}
//		return getId();
//	}

//	public void setEntity(String simpleName) {
//		// TODO Auto-generated method stub
//		
//	}
}
