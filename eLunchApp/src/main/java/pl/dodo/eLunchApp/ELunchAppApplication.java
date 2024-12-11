package pl.dodo.eLunchApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("pl.dodo.eLunchApp.repository")
@EnableCaching
public class ELunchAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ELunchAppApplication.class, args);
	}

}
