import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import models.Dept;

import repos.DeptRepository;


@SpringBootApplication(scanBasePackages="repos")
@EnableJpaRepositories(basePackages="repos")
@EntityScan(basePackages="models")
@EnableDiscoveryClient
public class DbApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(DbApplication.class, args);
	}
	


	@Bean
	public DeptRepository createdeptrepo(DeptRepository repo){
		for(int i = 10; i< 150;i+=10)
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
		return repo;
	}

	

	
}
