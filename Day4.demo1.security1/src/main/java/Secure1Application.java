
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import services.MyBusinessLogic;

@SpringBootApplication(scanBasePackages="sec, controllers, services")
public class Secure1Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Secure1Application.class, args);
		MyBusinessLogic logic =  ctx.getBean("mylogic", MyBusinessLogic.class);
		
		SecurityContext securitycontext = new SecurityContextImpl();
		Authentication auth = new UsernamePasswordAuthenticationToken("user4", "pass4");
		securitycontext.setAuthentication(auth);
		SecurityContextHolder.setContext(securitycontext);
		
		try {
			logic.m1();
		} catch (Exception e) {
			System.out.println( "error for m1 " + e);
		}
		
		try{
			logic.m2();
		} catch (Exception e) {
			System.out.println( "error for m2 " + e);
		}

		}
		
	}

