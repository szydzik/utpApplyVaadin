package pl.edu.utp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.utp.model.Department;

import java.util.List;

/**
 * Created by Tomala on 2017-01-07.
 */
public interface DepartmentRepository extends CrudRepository<Department, Long> {

    List<Department> findAll();
}

