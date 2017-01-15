package pl.edu.utp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import pl.edu.utp.model.user.Privilege;
import pl.edu.utp.model.user.Role;
import pl.edu.utp.model.user.User;
import pl.edu.utp.repository.security.PrivilegeRepository;
import pl.edu.utp.repository.security.RoleRepository;
import pl.edu.utp.repository.security.UserRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;

/**
 * Created by xxbar on 15.01.2017.
 */
@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
//    @Autowired
//    private PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (alreadySetup)
            return;
        Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        createRoleIfNotFound("ROLE_ADMIN", Arrays.asList(readPrivilege, writePrivilege));
        createRoleIfNotFound("ROLE_USER", Arrays.asList(readPrivilege));

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Test");
//        security.setPassword(passwordEncoder.encode("test"));
        user.setPassword("test");
        user.setEmail("test@test.com");
        user.setRoles(Arrays.asList(adminRole));
        user.setEnabled(true);
        userRepository.save(user);

        alreadySetup = true;
    }


    @Transactional
    private Privilege createPrivilegeIfNotFound(String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilegeRepository.save(privilege);
        }
        return privilege;
    }

    @Transactional
    private Role createRoleIfNotFound(String name, Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
            role.setPrivileges(privileges);
            roleRepository.save(role);
        }
        return role;
    }
}
