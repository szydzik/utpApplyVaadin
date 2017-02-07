package pl.edu.utp.security;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.utp.model.security.Function;
import pl.edu.utp.model.security.Role;
import pl.edu.utp.model.security.User;
import pl.edu.utp.repository.UserRepository;
import pl.edu.utp.view.AccessControlView;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by szydzik on 11.01.2017.
 */
@SpringComponent
@VaadinSessionScope
public class UserSessionComponent {

    private User currentUser;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FunctionCacheComponent functionCacheComponent;

    @Autowired
    AccessControlView accessControlView;

    @PostConstruct
    private void postConstruct(){

    }

    public UserSessionComponent() {
    }

    boolean isCurrentUser(){
        return currentUser != null;
    }

    public void refreshUserFromContext() {
        String username;
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        username = ((UserDetails) o).getUsername();
        if (username != null){
            User u = userRepository.findByLogin(username);
            setCurrentUser(u);
            accessControlView.reloadPrivileges(username);
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<Role> getUserRole(){
        return isCurrentUser() ? getCurrentUser().getRoles() : new ArrayList<>();
    }

    public Set<Function> getUserFunctions(){
        if (isCurrentUser()){
            Set<Function> set = new HashSet<>();
            getCurrentUser().getRoles().stream().forEach(r->{
                r.getFunctions().stream().forEach(f -> set.add(f));
            });
            return set;
        }else{
            return new HashSet<>();
        }
    }

}
