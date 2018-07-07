package demo;

import java.lang.reflect.Array;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootApplication
@RestController(value = "/app")
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class HystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(HystrixApplication.class, args);
	}

	@Bean
	public RestTemplate template() {
		return new RestTemplate();
	}

	@Autowired
	private RestTemplate template;

	@GetMapping(value="demo1")
	@HystrixCommand(commandKey="default",groupKey="dbapp",fallbackMethod="myfallbackmethod")
	public Object[] process() {
		String url = "http://localhost:8080/dept?loc=hyd";
		ResponseEntity<Object[]> arr = template.getForEntity(url, Object[].class);
		return  arr.getBody();
	}
	
	public Object[] myfallbackmethod(){
		System.out.println(" myfallbackmethod invoked ... ");
		return new Object[0];
	}

}
