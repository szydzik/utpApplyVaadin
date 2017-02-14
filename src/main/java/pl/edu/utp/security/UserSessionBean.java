package pl.edu.utp.security;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import pl.edu.utp.model.security.Function;
import pl.edu.utp.model.security.Role;
import pl.edu.utp.model.security.User;
import pl.edu.utp.repository.UserRepository;
import pl.edu.utp.utils.LoggerUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by szydzik on 11.01.2017.
 */
@SpringComponent
@VaadinSessionScope
public class UserSessionBean implements Serializable {

    private User currentUser;

    @Autowired
    UserRepository userRepository;

    @Autowired
    FunctionCacheBean functionCacheBean;

    @Autowired
    PriviledgesBean priviledgesBean;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSessionBean.class);

    @PostConstruct
    private void postConstruct(){
        refreshUserFromContext();

    }

    public UserSessionBean() {
    }

    boolean isCurrentUser(){
        return currentUser != null;
    }

    public void refresh(){
        refreshUserFromContext();

    }

    /**
     * Pobieranie loginu użytkownika z sesji i ładowanie użytkownika z bazy danych
     *
     */
    private void refreshUserFromContext() {
        LOGGER.info(LoggerUtils.getSeparator());
        String username = getUserName();
        LOGGER.info("User from session: {}", username);

        currentUser = getUser(username);
        LOGGER.info("User from db: {}", currentUser);
        LOGGER.info(LoggerUtils.getSeparator());

        //przeładowanie uprawnień
        priviledgesBean.reload(currentUser.getLogin());
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

    /**
     * Pobieranie loginu użytkownika.
     *
     * @return
     */
    public String getUserName() {
        Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userLogin = ((UserDetails) o).getUsername();
        return userLogin;
    }

    /**
     * Pobieranie użytkownika.
     *
     * @return
     */
    public User getUser(String username) {
        if (username != null && !username.isEmpty()){
            return userRepository.findByLogin(username);
        }
        return null;
    }

    /**
     * Pobieranie bieżących ról użytkownika.
     *
     * @return
     */
    public List<Role> getCurrentRoles() {
        return currentUser != null ? currentUser.getRoles() : new ArrayList<>();
    }

    /**
     * Pobieranie bieżących ról użytkownika.
     *
     * @return
     */
    public List<String> getCurrentRolesString() {
        List<String> result = new ArrayList<>();
        for (Role role : getCurrentRoles()) {
            result.add(role.getName());
        }
        return result;
    }

//    /**
//     * Zwraca obiekt funkcji dla danego enuma i trybu widoku.
//     *
//     * @param action
//     * @return
//     */
//    public Function getFunction(FunctionCodeEnum action, ViewMode viewMode) {
//        return functionCacheComponent.getFunction(action, viewMode);
//    }
    public Function getFunction(FunctionCodeEnum action) {
        return functionCacheBean.getFunction(action);
    }

    /**
     * Zwraca, czy użytkownik ma dostęp do danej funkcji. Używane przy tworzeniu
     * menu.
     *
     * @param functionCodeEnum
     * @return
     */
    public boolean hasAccess(FunctionCodeEnum functionCodeEnum) {
        return priviledgesBean.hasPriviledges(functionCodeEnum, functionCodeEnum.getViewMode());
    }

}
