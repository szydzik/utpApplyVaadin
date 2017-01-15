package pl.edu.utp.repository.security;

import org.springframework.data.repository.CrudRepository;
import pl.edu.utp.model.user.Role;

/**
 * Created by xxbar on 15.01.2017.
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    Role findByName(String name);

}
