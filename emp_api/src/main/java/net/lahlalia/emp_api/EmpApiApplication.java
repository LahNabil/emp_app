package net.lahlalia.emp_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmpApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpApiApplication.class, args);
	}

}
