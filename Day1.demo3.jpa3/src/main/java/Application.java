import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import models.Dept;

import repos.DeptRepository;


@SpringBootApplication(scanBasePackages="repos")
@EnableJpaRepositories(basePackages="repos")
@EntityScan(basePackages="models")
public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
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

	@Bean
	public DeptRepository createdeptrepo(DeptRepository deptrepo){
		return deptrepo;
	}

	

	
}
