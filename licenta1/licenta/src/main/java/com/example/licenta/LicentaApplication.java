package com.example.licenta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
		scanBasePackages = {
				"com.example.licenta",
				"com.example.amqp",
		}
)
@EnableFeignClients(
		basePackages = "com.example.clients"
)
@PropertySources({
		@PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class LicentaApplication {

	public static void main(String[] args) {
		SpringApplication.run(LicentaApplication.class, args);
	}

}
