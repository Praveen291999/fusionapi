package fusionapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "fusionapi")
public class FusionapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FusionapiApplication.class, args);
	}

}
