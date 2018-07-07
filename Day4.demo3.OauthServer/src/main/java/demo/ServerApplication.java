package demo;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	
	@GetMapping(value="/user",produces=MediaType.APPLICATION_JSON_VALUE)
	public Principal getuser(Principal pri)
	{
		System.out.println("in getuser " + pri);
		return pri;
	}
}
