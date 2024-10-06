package net.lahlalia.emp_api;

import net.lahlalia.emp_api.entities.Role;
import net.lahlalia.emp_api.repositories.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmpApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpApiApplication.class, args);
	}
	@Bean
	public CommandLineRunner runner(RoleRepository roleRepository){
		return args -> {
			if(roleRepository.findByName("USER").isEmpty()){
				roleRepository.save(
						Role.builder().name("USER")
								.build()
				);
			}
		};
	}

}
