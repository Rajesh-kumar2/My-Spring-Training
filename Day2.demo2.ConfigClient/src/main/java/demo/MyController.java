package demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="/")
@RefreshScope
public class MyController {
	@Value(value="${message:hi from me but property is not loaded}")
	private String msg;
	
	@GetMapping()
	public String disp(){
		return "<h1> Msg = " + this.msg;
	}
}
