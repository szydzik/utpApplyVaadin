package pl.edu.utp.security;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.model.security.Function;
import pl.edu.utp.model.security.Role;
import pl.edu.utp.repository.RoleRepository;
import pl.edu.utp.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by szydzik on 07.02.2017.
 */
@SpringComponent
@VaadinSessionScope
public class PriviledgesComponent {

    private List<String> availableFunctions = new ArrayList<>();

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @PostConstruct
    private void postConstruct(){
        reload("admin");
    }

    public void reload(String userLogin) {
        System.out.println("===============================================================================");
        Set<Function> functions;

        if (userLogin != null && !userLogin.equals("anonymousUser") && !userLogin.isEmpty()) {
            System.out.println("====== Odświeżam uprawnienia do funkcji dla: "+userLogin);
            functions = getFunctionsByLogin(userLogin);
        }else{
            System.out.println("====== Odświeżam uprawnienia do funkcji dla anonimowego użytkownika: "+userLogin);
            functions = getFunctionsForAnonymousUser();
        }
        for (Function f : functions){
            availableFunctions.add(f.getCode());
        }
        System.out.println("====== Użytkownikowi przydzielono: [ "+this.availableFunctions.size()+" ] uprawnień.");
        availableFunctions.forEach(System.out::println);
        System.out.println("===============================================================================");

    }

    private Set<Function> getFunctionsByLogin(String login){
        Set<Function> results = new HashSet<>();
        List<Role> roles = userRepository.findByLogin(login).getRoles();
        for (Role r : roles){
            List<Function> functions = roleRepository.findByName(r.getName()).getFunctions();
            if (functions != null) results.addAll(functions);
        }
        return results;
    }

    private Set<Function> getFunctionsForAnonymousUser(){
        Set<Function> results = new HashSet<>();
        Role role = roleRepository.findByName("ROLE_ANONYMOUS");
        results.addAll(role.getFunctions());
        return results;
    }

    public boolean hasPriviledges(FunctionCodeEnum function) {
        return availableFunctions.contains(function.name());
    }

    public boolean hasPriviledges(FunctionCodeEnum functionCodeEnum, ViewMode viewMode) {
        String functionCodeName = functionCodeEnum.name();
        if (Arrays.asList(ViewMode.CREATE, ViewMode.DETAILS, ViewMode.EDIT, ViewMode.DELETE).contains(viewMode)) {
            functionCodeName = functionCodeEnum.name() + "_" + viewMode.name();
        }
        return availableFunctions.contains(functionCodeName);
    }

}
