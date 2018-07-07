

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import models.Dept;
import repos.DeptRepository;

@SpringBootApplication(scanBasePackages="controllers")
@EnableJpaRepositories(basePackages="repos")
@EntityScan(basePackages="models")
public class DBApplication {

	public static void main(String[] args) {
		SpringApplication.run(DBApplication.class, args);
	}
	@Bean
	public DeptRepository repo(DeptRepository repo){
		int cnt =10;
		for(int i = cnt; i< (cnt + 100);i+=10)
		{
				Dept dept = new Dept(i,"Nameof"+i,"hyd");
				if ((i %20)==0)
						dept.setLoc("blr");
				repo.save(dept);
		}
		return repo;
	}
}
