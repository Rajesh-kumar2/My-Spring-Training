package demo;

import java.util.List;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Dept;
import repos.DeptRepository;
import repos.EmpRepository;

@SpringBootApplication()
@EnableJpaRepositories(basePackages="repos")
@EntityScan(basePackages="models")
@RestController( value="/")
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext  ctx = SpringApplication.run(Application.class, args);
		deptdemo(ctx.getBean("createdeptrepo",DeptRepository.class));
		
	}
	public static void deptdemo(DeptRepository repo){
		for(int i = 10; i< 100;i+=10)
		{
				Dept dept = new Dept(i,"Nameof"+i,"Hyd");
				if ((i %20)==0)
						dept.setLoc("Blr");
				repo.save(dept);
		}
		repo.delete(20);
		Dept dept = new Dept(10,"Synechron","Pune");
		repo.save(dept);
		repo.findAll().forEach(System.out::println);
		System.out.println("\n\n");
		repo.findByLoc("Hyd").forEach(System.out::println);
	}
	@Autowired
	private DeptRepository deptrepo;
	
	@Bean
	public DeptRepository createdeptrepo(DeptRepository deptrepo){
		return deptrepo;
	}
	@Bean
	public EmpRepository createemprepo(EmpRepository emprepo){
		return emprepo;
	}
	@GetMapping
	public String getdata(){
		Iterable<Dept> iterable= deptrepo.findAll();
		String str = "<table bgcolor='cyan' border='1'><tr><td>Deptno</td><td>DName</td><td>Loc</td></tr>" ;
		for (Dept dept : iterable) {
			str+= " <tr><td>"+ dept.getDeptno() +"</td><td>"+dept.getDname()+"</td><td>"+ dept.getLoc() 
			+"</td></tr>" ;			
		}
		str+= "</table>";
		return str;
	}

}
