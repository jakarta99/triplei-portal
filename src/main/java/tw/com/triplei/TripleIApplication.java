package tw.com.triplei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("tw.com.triplei.dao")
@EntityScan(basePackages = {"tw.com.triplei.entity"})
@ComponentScan("tw.com.triplei")
public class TripleIApplication {

	public static void main(String[] args) {
		SpringApplication.run(TripleIApplication.class, args);
	}
}
