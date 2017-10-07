package io.zandona.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	
	@GetMapping("/hello")
	@PreAuthorize("#oauth2.hasScope('service_read') and hasAuthority('ROLE_OPERATOR')")
	public String hello() {
		return "Hello authenticated user: " + SecurityContextHolder.getContext().getAuthentication().getPrincipal() + "!";
	}
	
}
