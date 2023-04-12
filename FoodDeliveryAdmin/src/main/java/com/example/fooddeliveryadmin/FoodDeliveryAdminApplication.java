package com.example.fooddeliveryadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@EnableFeignClients
public class FoodDeliveryAdminApplication {

	@Bean
	public WebClient getFoodDeliveryAppWebClient(WebClient.Builder webClientBuilder) {

		return webClientBuilder.clone().baseUrl("http://localhost:8080")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(FoodDeliveryAdminApplication.class, args);
	}

}
