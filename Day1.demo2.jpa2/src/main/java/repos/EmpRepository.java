package repos;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import models.Dept;
import models.Emp;

public interface EmpRepository extends JpaRepository<Emp, Integer> {
//	1. show all emps for a project
	public List<Emp> findByProject(String proj,Sort sort);
//	2. where salary > x 
	public List<Emp> findBySalaryGreaterThan(double salary);
//	3. where salary < x
	public List<Emp> findBySalaryLessThan(double salary);
//	4. where salary > x and < y
	public  List<Emp> findBySalaryBetween(double minsal, double maxsal);
	public  List<Emp> findByEnameBetween(String min, String max);
//	5. where project=proj1 and name is aa
	public  List<Emp> findByProjectAndEname(String proj, String ename);
//	6. where project=proj1 or name is aa
	public  List<Emp> findByProjectOrEname(String proj, String ename);
//  7. where project=proj1 and com in x and y 	
	//public  List<Emp> findByProjectAndComBetween(String proj, double minc, double maxc);
	@Query("select e from Emp e where e.project = ? and e.com between ? and ? ")
	public  List<Emp> myqueryforcom(String proj, double minc, double maxc);
}
