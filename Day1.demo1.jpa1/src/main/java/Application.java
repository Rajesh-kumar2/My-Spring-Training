import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;

import models.Dept;
import models.Emp;
import repos.DeptRepository;
import repos.EmpRepository;

@SpringBootApplication(scanBasePackages="repos")
@EnableJpaRepositories(basePackages="repos")
@EntityScan(basePackages="models")
public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		DeptRepository repo = ctx.getBean("createdeptrepo", DeptRepository.class);
	//	deptdemo(repo);
		EmpRepository repo1 = ctx.getBean("createemprepo", EmpRepository.class);
		query(repo1);
	//	empdemo(repo1);
	}
	public static void query(EmpRepository repo){
	//	repo.findByProject("proj1").forEach(System.out::println);
	//	repo.findBySalaryGreaterThan(5000).forEach(System.out::println);
	//	repo.findBySalaryLessThan(5000).forEach(System.out::println);
		repo.findBySalaryBetween(2000, 5000).forEach(System.out::println);
		repo.findByEnameBetween("Nameof1","Nameof5").forEach(System.out::println);
	//	repo.findByProjectOrEname("proj3", "Nameof9").forEach(System.out::println);
		//repo.findByProjectAndComBetween("proj3",15.35, 99.5).forEach(System.out::println);
	//	repo.myqueryforcom("proj3",15.35, 99.5).forEach(System.out::println);
		
	}
	public static void empdemo(EmpRepository repo){
		for(int i = 1; i< 10;i++)
		{
				Emp emp = new Emp();
				emp.setEname("Nameof"+i);
				emp.setProject("proj" + ((i % 3) +1));
				emp.setSalary(i* 1000);
				emp.setBdate(new Date());
				emp.setCom(Math.random()*100);
				repo.save(emp);
		}
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
	@Bean
	public EmpRepository createemprepo(EmpRepository emprepo){
		return emprepo;
	}
	

	
}
