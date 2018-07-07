package repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import models.Dept;
@Repository
public interface DeptRepository extends CrudRepository<Dept, Integer> {
	public List<Dept> findByLoc(String loc);
}
