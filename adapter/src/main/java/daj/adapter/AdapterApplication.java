package daj.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
	scanBasePackages = {
		"daj.adapter.product",
		"daj.adapter.common",
		"daj.product.service",
		"daj.user.service",
		"daj.adapter.user",
		"daj.adapter.errorHandler"
	}
)
public class AdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdapterApplication.class, args);
	}

}
