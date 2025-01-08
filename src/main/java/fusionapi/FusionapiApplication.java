package fusionapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = "fusionapi.repository.mongo")
@EnableJpaRepositories(basePackages = "fusionapi.repository.jpa")
@SpringBootApplication
public class FusionapiApplication {
	public static void main(String[] args) {
		SpringApplication.run(FusionapiApplication.class, args);
	}
}
