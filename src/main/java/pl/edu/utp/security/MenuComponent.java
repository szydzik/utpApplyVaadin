package pl.edu.utp.security;

import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.commons.ui.MenuConfig;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by szydzik on 08.02.2017.
 */
@SpringComponent
@VaadinSessionScope
public class MenuComponent implements Serializable {

    @Autowired
    private UserSessionComponent userSessionComponent;

    private final Map<String, MenuConfig> tabs = new LinkedHashMap<>();

    @PostConstruct
    private void postConstruct() {

        MenuConfig home = createMenuConfig("Home", FontAwesome.HOME);
        home.addMenuItem(FunctionCodeEnum.HOME);
        home.addMenuItem(FunctionCodeEnum.USER_HOME);

        MenuConfig admin = createMenuConfig("Admin", FontAwesome.USER_MD);
        admin.addMenuItem(FunctionCodeEnum.ADMIN_HOME);
        admin.addMenuItem(FunctionCodeEnum.ADMIN_SECRET);
        admin.addMenuItem(FunctionCodeEnum.USER_LIST);

    }

    private MenuConfig createMenuConfig(String name, FontAwesome icon) {
        MenuConfig menuConfig = new MenuConfig(name, icon, userSessionComponent);
        tabs.put(menuConfig.getName(), menuConfig);
        return menuConfig;
    }

    public List<MenuConfig> getMenuTabs() {
        return tabs.values().stream().filter(mc -> mc.isAnyActive()).collect(Collectors.toList());
    }

    public List<Object[]> getMenuModel(String tabName) {
        return tabs.get(tabName).getMenuModel();
    }

    public List<FunctionCodeEnum> getAllMenuFunctionCodeEnums() {
        return getMenuTabs().stream()
                .flatMap(mt -> mt.getMenuModel().stream())
                .map(mm -> (FunctionCodeEnum) mm[1])
                .collect(Collectors.toList());
    }
}
