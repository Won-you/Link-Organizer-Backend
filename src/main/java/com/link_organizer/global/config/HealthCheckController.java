package com.link_organizer.global.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
	
	@GetMapping("/api/health")
	public ResponseEntity<String> healthCheck() {
		return ResponseEntity.ok("Server is alive!");
	}
}
