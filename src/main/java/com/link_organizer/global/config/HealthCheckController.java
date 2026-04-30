package com.link_organizer.global.config;

import com.link_organizer.common.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
	
	@GetMapping("/api/health")
	public ApiResponse<String> healthCheck() {
		return ApiResponse.success("Server is alive");
	}
}
