package com.example.notifications;

import org.springframework.boot.SpringApplication;
import com.example.amqp.RabbitMQMessageProducer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication(
		scanBasePackages = {
				"com.example.notifications",
				"com.example.amqp",
		}
)
@PropertySources({
		@PropertySource("classpath:clients-${spring.profiles.active}.properties")
})
public class NotificationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificationsApplication.class, args);
	}

}
