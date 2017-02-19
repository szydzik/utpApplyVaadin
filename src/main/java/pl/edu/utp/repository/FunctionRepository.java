package pl.edu.utp.repository;

import org.springframework.data.repository.Repository;
import pl.edu.utp.model.security.Function;

import java.util.List;

/**
 * Created by szydzik on 05.02.2017.
 */
public interface FunctionRepository extends Repository<Function, Long> {
    List<Function> findAll();
    List<Function> findByCodeStartsWithIgnoreCase(String code);
    Function findByName(String name);
}
