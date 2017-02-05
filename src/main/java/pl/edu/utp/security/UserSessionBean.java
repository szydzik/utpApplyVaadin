package pl.edu.utp.security;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import pl.edu.utp.model.security.User;
import pl.edu.utp.repository.UserRepository;

import javax.annotation.PostConstruct;
import java.security.Principal;

/**
 * Created by xxbar on 11.01.2017.
 */
@SpringComponent
@VaadinSessionScope
public class UserSessionBean {

    String userLogin = "test";

    private User currentUser;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FunctionCacheBean functionCacheBean;

    @PostConstruct
    private void postConstruct(){

    }

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
//    public String getUserName() {
//        String name = userLogin;
//        Principal principal = (Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println("=====Principal: "+principal.getName());
//        if (principal != null){
//            return principal.getName();
//        }
//        return name;
//    }

}
