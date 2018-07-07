package demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="/")
public class MyController {
	@GetMapping()
	public String method1(){
		return "<h1>App2 invoked ...</h1>";
	}
	
}
