package pt.abba.abbachurch;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class AbbaChurchApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbbaChurchApplication.class, args);
	}

	@PostConstruct
	protected void init() {
		log.info("http://localhost:8080/download?url=");
	}
}
