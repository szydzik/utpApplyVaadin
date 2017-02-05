package pl.edu.utp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.utp.model.security.Function;

import java.util.List;

/**
 * Created by szydzik on 05.02.2017.
 */
public interface FunctionRepository extends CrudRepository<Function, Long> {
    List<Function> findAll();
}
