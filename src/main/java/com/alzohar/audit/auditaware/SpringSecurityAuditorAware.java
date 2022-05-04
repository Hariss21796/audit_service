package com.alzohar.audit.auditaware;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.alzohar.audit.entity.Product;

public class SpringSecurityAuditorAware implements AuditorAware<String> {
	

	@Override
	public Optional<String> getCurrentAuditor() {

		// Just return a string representing the username

		return Optional.ofNullable(getUsername()).filter(s -> !s.isEmpty());
	}

	public String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			return currentUserName;
		}
		return null;
	}

}
