package repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import models.Dept;

public interface DeptRepository extends JpaRepository<Dept, Integer> {
	List<Dept> findByLoc(String loc);
	
}
