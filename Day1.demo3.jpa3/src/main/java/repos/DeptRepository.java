package repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import models.Dept;
@RepositoryRestResource(collectionResourceRel = "dept", path = "departments")
public interface DeptRepository extends CrudRepository<Dept, Integer> {
	List<Dept> findByLoc(String loc);
}
