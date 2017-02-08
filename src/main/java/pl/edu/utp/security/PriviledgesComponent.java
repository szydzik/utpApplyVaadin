package pl.edu.utp.security;

import com.vaadin.spring.access.ViewAccessControl;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.VaadinSessionScope;
import com.vaadin.ui.UI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import pl.edu.utp.model.security.Function;
import pl.edu.utp.model.security.Role;
import pl.edu.utp.repository.RoleRepository;
import pl.edu.utp.repository.UserRepository;
import pl.edu.utp.utils.LoggerUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.*;

/**
 * Created by szydzik on 07.02.2017.
 */
@SpringComponent
@VaadinSessionScope
public class PriviledgesComponent implements ViewAccessControl, Serializable{

    private List<String> availableFunctions = new ArrayList<>();
//    private List<FunctionCodeEnum> functionCodeEnums = new ArrayList<>();
    private Set<String> allowedViews = new HashSet<>();

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(PriviledgesComponent.class);

    @PostConstruct
    private void postConstruct(){
    }

    public void reload(String userLogin) {
        LOGGER.info(LoggerUtils.getSeparator());
        Set<Function> functions;

        if (userLogin != null && !userLogin.equals("anonymousUser") && !userLogin.isEmpty()) {
            LOGGER.info("===== Odświeżam uprawnienia do funkcji dla: {}", userLogin);
            functions = getFunctionsByLogin(userLogin);
        }else{
            LOGGER.info("===== Odświeżam uprawnienia do funkcji dla anonimowego użytkownika: {}", userLogin);
            functions = getFunctionsForAnonymousUser();
        }
        for (Function f : functions){
            availableFunctions.add(f.getFunctionEnum());
        }
        LOGGER.info("===== Użytkownikowi przydzielono: [ {} ] uprawnień:", this.availableFunctions.size());
        availableFunctions.forEach(i -> LOGGER.info("===== --- {}",i));
        for (String s : availableFunctions){
            if (s != null) {
                allowedViews.add(FunctionCodeEnum.valueOf(s).getView());
            }
        }
        LOGGER.info("===== Użytkownikowi przydzielono: [ {} ] widoków:", allowedViews.size());
        allowedViews.forEach(i -> LOGGER.info("===== --- {}",i));
        LOGGER.info(LoggerUtils.getSeparator());

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

    @Override
    public boolean isAccessGranted(UI ui, String beanName) {
        final SpringView annotation = applicationContext.findAnnotationOnBean(beanName, SpringView.class);
        if (annotation != null) {
            return allowedViews.contains(annotation.name());
        } else {
            return false;
        }
    }
}
