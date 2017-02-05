package pl.edu.utp;

import org.springframework.security.core.context.SecurityContextHolder;
import pl.edu.utp.model.security.User;

import java.security.Principal;

/**
 * Created by xxbar on 11.01.2017.
 */
//@VaadinSessionScope
public class UserSessionBean {

    String userLogin = "test";

    private User currentUser;

//    @Autowired
//    UserRepository userRepository;

    public UserSessionBean() {
//        getUserName();
    }

    boolean isCurrentUser(){
        return currentUser != null;
    }

    /**
     * Pobieranie loginu użytkownika.
     *
     * @return
     */
    public String getUserName() {
        String name = userLogin;
        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("=====Principal: "+principal);
        if (principal != null){
            return principal.getName();
        }
        return name;
    }

}
