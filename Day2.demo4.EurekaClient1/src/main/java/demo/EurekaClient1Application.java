package demo;

import java.util.List;

import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.ribbon.RequestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaClient1Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaClient1Application.class, args);
	}
	@Bean
	@LoadBalanced
	public RestTemplate resttemplate(){
			return new RestTemplate();
		}
}

@RestController
class ServiceInstanceRestController {

	@Autowired
	private DiscoveryClient discoveryClient;
	@RequestMapping("/service-instances/{applicationName}")
	public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
		return this.discoveryClient.getInstances(applicationName);
	}
	
	@Autowired
	private RestTemplate template;

	
	@GetMapping(value = "/count")
	public String countofrecords() {
	//	ResponseEntity<String> str = template.getForEntity("http://localhost:8082/departments", String.class);
		ResponseEntity<String> str = template.getForEntity("http://client2/departments", String.class);
	//	System.out.println(str);
		int count = 0;
		try {
			JSONObject obj = new JSONObject(str.getBody());
			count = obj.getJSONObject("_embedded").getJSONArray("dept").length();
		} catch (Exception e) { 				e.printStackTrace(); 		}
		return "<h1> Records =  " + count + "</h1>";
	}

}
