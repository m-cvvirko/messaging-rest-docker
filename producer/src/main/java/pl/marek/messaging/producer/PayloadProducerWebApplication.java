package pl.marek.messaging.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayloadProducerWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayloadProducerWebApplication.class, args);
	}
}
