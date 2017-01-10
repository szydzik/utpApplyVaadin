package pl.edu.utp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.utp.model.University;

import java.util.List;

/**
 * Created by Bartosz Szydzik on 06.01.2017.
 */
public interface UniversityRepository extends CrudRepository<University, Long> {

    List<University> findAll();

}
