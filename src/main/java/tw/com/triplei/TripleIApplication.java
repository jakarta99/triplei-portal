package tw.com.triplei;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.endpoint.jmx.DataEndpointMBean;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

import io.searchbox.client.JestClient;
import io.searchbox.core.Index;

@SpringBootApplication
@EnableJpaRepositories("tw.com.triplei.dao")
@EntityScan(basePackages = {"tw.com.triplei.entity"})
@ComponentScan("tw.com.triplei")
public class TripleIApplication {
	
	@Autowired
	private JestClient jestClient;
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Scheduled(fixedRate = 10000)
	public void transfer() {
		String url = "http://localhost:8080/jolokia/read/org.springframework.boot:type=Endpoint,name=metricsEndpoint/Data"; 
		String result = 
				restTemplate.getForObject(url, String.class);
		
		System.out.println(result);
		
		Index index = new Index.Builder(result).index("metrics2").type("metric").id(UUID.randomUUID().toString()).build();
		
		try {
			jestClient.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

	public static void main(String[] args) {
		SpringApplication.run(TripleIApplication.class, args);
	}
}
