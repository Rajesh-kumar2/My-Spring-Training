package demo;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/")
@RestController
public class MyController {
	@GetMapping()
	public String simple() {
		return "<h1>Index Page</h1>" + "<h1><a href='admin/m1'>Method1 of Admin</a></h1>"
				+ "<h1><a href='admin/m2'>Method2 of Admin</a></h1>"+
		"<h1><a href='admin/user'>UserInfo </a></h1>";
	}

	@GetMapping(value = "admin/m1")
	public String m1() {
		return "<h1>m1 of admin invoked .... </h1><h2><a href='/app'>Back to index page</a></h2>";
	}

	@GetMapping(value = "admin/m2")
	public String m2() {
		return "<h1>m2 of admin invoked .... </h1><h2><a href='/app'>Back to index page</a></h2>";
	}
	 @RequestMapping("admin/user")
	  public Principal user(Principal principal) {
	    return principal;
	  }

	@RequestMapping("/admin/login")
	public String dashboard() {
		return "redirect:/#/";
	}

}
