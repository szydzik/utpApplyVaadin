package pl.edu.utp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.utp.model.Course;

import java.util.List;

/**
 * Created by Bartosz Szydzik on 06.01.2017.
 */
public interface CourseRepository extends CrudRepository<Course, Long> {
    List<Course> findAll();
}
