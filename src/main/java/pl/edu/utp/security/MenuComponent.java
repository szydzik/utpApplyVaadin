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
        refresh();
    }

    private MenuConfig createMenuConfig(String name, FontAwesome icon) {
        MenuConfig menuConfig = new MenuConfig(name, icon, userSessionComponent);
        tabs.put(menuConfig.getName(), menuConfig);
        return menuConfig;
    }

//    public List<MenuConfig> getMenuTabs() {
//        return tabs.values().stream().filter(MenuConfig::isAnyActive).collect(Collectors.toList());
//    }

    public Map<String, MenuConfig> getTabs() {
        return tabs;
    }

    public List<MenuItemModel> getMenuModel(String tabName) {
        return tabs.get(tabName).getMenuModel();
    }

//    public List<FunctionCodeEnum> getAllMenuFunctionCodeEnums() {
//        return getMenuTabs().stream()
//                .flatMap(mt -> mt.getMenuModel().stream())
//                .map(mm -> mm.getFuncionCodeEnum())
//                .collect(Collectors.toList());
//    }

    public void refresh(){
        tabs.clear();

        MenuConfig home = createMenuConfig("Home", FontAwesome.HOME);
        home.addMenuItem(FunctionCodeEnum.HOME);

        MenuConfig admin = createMenuConfig("Admin", FontAwesome.USER_MD);
        admin.addMenuItem(FunctionCodeEnum.HOME);
        admin.addMenuItem(FunctionCodeEnum.ADMIN_HOME);
        admin.addMenuItem(FunctionCodeEnum.ADMIN_SECRET);
        admin.addMenuItem(FunctionCodeEnum.USER_LIST);

        MenuConfig user = createMenuConfig("User", FontAwesome.USER);
        user.addMenuItem(FunctionCodeEnum.HOME);
        user.addMenuItem(FunctionCodeEnum.USER_HOME);
    }
}
