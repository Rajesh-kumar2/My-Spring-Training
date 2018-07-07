package repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import models.Dept;

public interface DeptRepository extends CrudRepository<Dept, Integer> {
	List<Dept> findByLoc(String loc);
}
