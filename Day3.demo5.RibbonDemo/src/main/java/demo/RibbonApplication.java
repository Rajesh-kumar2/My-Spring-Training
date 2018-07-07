package demo;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.ribbon.RequestTemplate;

@SpringBootApplication
@RibbonClient(name = "db-app", configuration = MyConfiguration.class)
@RestController(value="/")
public class RibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(RibbonApplication.class, args);
	}
	@Bean
	@LoadBalanced
	public RestTemplate template(){
		return new RestTemplate();
	} 
@Autowired
	private RestTemplate template;
	@GetMapping
	public String process(){
	//	String url = "http://localhost:8080/dept/byloc?loc=hyd";
		String url = "http://db-app/dept?loc=hyd";
		ResponseEntity<String> str =  template.getForEntity(url, String.class);
		return str.getBody();
	}
			
	
}
