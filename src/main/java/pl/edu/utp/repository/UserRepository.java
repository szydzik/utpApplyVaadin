package pl.edu.utp.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.utp.model.security.User;

import java.util.List;

/**
 * Created by xxbar on 11.01.2017.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
}
