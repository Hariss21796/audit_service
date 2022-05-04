package com.alzohar.audit.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alzohar.audit.entity.AuditEntity;
import com.alzohar.audit.entity.Product;

@Repository
public interface AuditRepository extends JpaRepository<AuditEntity, Long> {
	
//	public static  AuditEntity saveAudit(AuditEntity audit) {
//		return audit;
//		
//	}


//	@Query("SELECT a FROM audit_service a WHERE a.createdDate =:createdDate")
//	public AuditService getByDate(@Param("createdDate") Date createdDate);

}
