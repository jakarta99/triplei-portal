package tw.com.triplei;

import java.io.IOException;
import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

@SpringBootApplication
@EnableJpaRepositories("tw.com.triplei.dao")
//@EntityScan(basePackages = { "tw.com.triplei.entity" })
@EntityScan(basePackageClasses = {TripleIApplication.class, Jsr310Converters.class})
@ComponentScan("tw.com.triplei")
public class TripleIApplication {
	
	private RestTemplate restTemplate = new RestTemplate();


	//@Scheduled(fixedRate = 5000)
	public void transfer() {
		String url = "http://localhost:8080/jolokia/read/org.springframework.boot:type=Endpoint,name=metricsEndpoint/Data";  //*請修改為該 rpc 的 port *
		String result = restTemplate.getForObject(url, String.class);

		result = result.replaceAll("([a-z])\\.", "$1\\_"); //*增加把 "." 變成 "_" 避免 elasticSearch json mapping 的誤會*

		System.out.println(result);

		Index index = new Index.Builder(result).index("metrics").type("metric").id(UUID.randomUUID().toString())
				.build();

		try {

			HttpClientConfig clientConfig = new HttpClientConfig.Builder("http://localhost:9200").multiThreaded(true).build(); //*請修改為該 elasticSearch 的 port *
			JestClientFactory factory = new JestClientFactory();
			factory.setHttpClientConfig(clientConfig);
			JestClient jestClient = factory.getObject();

			jestClient.execute(index);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SpringApplication.run(TripleIApplication.class, args);
	}
}
