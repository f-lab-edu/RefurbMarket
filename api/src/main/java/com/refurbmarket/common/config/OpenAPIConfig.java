package com.refurbmarket.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
@OpenAPIDefinition(
	info = @Info(title = "RefurbMarket API 명세서", description = "RefurbMarket API 명세서")
)
@Configuration
public class OpenAPIConfig {
	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI();
	}
}
