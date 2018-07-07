package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@SpringBootApplication
@EnableFeignClients
@RestController
@RequestMapping(value="/demo")
@EnableHystrix
@EnableHystrixDashboard
@EnableCircuitBreaker
public class FeignApplication {
	@Autowired
	private UserClient userclient;
	
	public static void main(String[] args) {
		SpringApplication.run(FeignApplication.class, args);
	}
	@Bean
	 public RequestInterceptor myinteceptor(){
		 return new RequestInterceptor(){
			 public void apply(RequestTemplate requestTemplate) {
				 requestTemplate.header("user-agent","Mozilla/5.0 (Windows NT 6.3; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36");
			 }
		 };
	 } 
	 
	@GetMapping(value="/checkuser")
	@HystrixCommand(commandKey="default", groupKey="dbapp", fallbackMethod="myfallbackmethod")
	public String print(){
		System.out.println(" in print ");
		User u1 = userclient.getUser().getData();
		String str = "<table bgcolor='cyan' border='1'><tr><td>Id</td><td>FirstName</td><td>LastName</td><td>Image</td></tr>" ;
		str+= " <tr><td>"+ u1.getId() +"</td><td>"+ u1.getFirst_name() +"</td><td>"+ u1.getLast_name() 
		+"</td><td><img src='"+ u1.getAvatar() +"'/></td></tr>" ;
		return str;
				
	}
	public String myfallbackmethod(){
		System.out.println(" myfallbackmethod invoked ... ");
		User u1 = new User();
		u1.setId(1); u1.setFirst_name("Vaishali" );u1.setLast_name("Tapaswi");u1.setAvatar("https://www.sample-videos.com/img/Sample-png-image-1mb.png");
		String str = "<table bgcolor='cyan' border='1'><tr><td>Id</td><td>FirstName</td><td>LastName</td><td>Image</td></tr>" ;
		str+= " <tr><td>"+ u1.getId() +"</td><td>"+ u1.getFirst_name() +"</td><td>"+ u1.getLast_name() 
		+"</td><td><img src='"+ u1.getAvatar() +"'/></td></tr>" ;
		return str;
	}
	
	
}
