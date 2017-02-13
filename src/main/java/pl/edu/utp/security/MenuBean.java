package pl.edu.utp.security;

import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.commons.ui.MenuConfig;
import pl.edu.utp.utils.TestIcon;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by szydzik on 08.02.2017.
 */
@SpringComponent
@VaadinSessionScope
public class MenuBean implements Serializable {

    private TestIcon testIcon = new TestIcon(100);

    @Autowired
    private UserSessionBean userSessionBean;

    private final List<MenuConfig> tabs = new LinkedList<>();

    @PostConstruct
    private void postConstruct() {
        refresh();
    }

    private MenuConfig createMenuConfig(String name, FontAwesome icon) {
        MenuConfig menuConfig = new MenuConfig(name, userSessionBean);
        tabs.add(menuConfig);
        return menuConfig;
    }

//    public List<MenuConfig> getMenuTabs() {
//        return tabs.values().stream().filter(MenuConfig::isAnyActive).collect(Collectors.toList());
//    }

    public List<MenuItemModel> getAllMenuItems(){
        List<MenuItemModel> list = new ArrayList<>();
        tabs.stream().forEach(i-> i.getMenuModel().stream().forEach(i2-> list.add(i2)));
        return list;
    }

    public List<MenuConfig> getTabs() {
        return tabs;
    }

    public void refresh(){
        tabs.clear();

        MenuConfig home = createMenuConfig("Home", null);
        home.addMenuItem(FunctionCodeEnum.HOME, FontAwesome.HOME);

        MenuConfig admin = createMenuConfig("Admin", null);
        admin.addMenuItem(FunctionCodeEnum.ADMIN_HOME, testIcon.getFontAvesomeIcon());
        admin.addMenuItem(FunctionCodeEnum.ADMIN_SECRET, testIcon.getFontAvesomeIcon());
        admin.addMenuItem(FunctionCodeEnum.USER_LIST, testIcon.getFontAvesomeIcon());

        MenuConfig user = createMenuConfig("User", null);
        user.addMenuItem(FunctionCodeEnum.USER_HOME, testIcon.getFontAvesomeIcon());
    }
}
