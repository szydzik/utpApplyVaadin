package pl.edu.utp.repository.security;

import org.springframework.data.repository.CrudRepository;
import pl.edu.utp.model.security.Privilege;

/**
 * Created by xxbar on 15.01.2017.
 */
public interface PrivilegeRepository extends CrudRepository<Privilege, Long> {
    Privilege findByName(String name);
}
