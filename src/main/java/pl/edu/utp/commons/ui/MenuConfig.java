package pl.edu.utp.commons.ui;

import com.vaadin.server.FontAwesome;
import pl.edu.utp.model.security.Function;
import pl.edu.utp.security.FunctionCodeEnum;
import pl.edu.utp.security.MenuItemModel;
import pl.edu.utp.security.UserSessionBean;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by szydzik on 07.02.2017.
 */
public class MenuConfig {

    private final String name;

    private final List<MenuItemModel> menuModel;
    private final UserSessionBean userSessionBean;

    public MenuConfig(String name, UserSessionBean userSessionBean) {
        this.name = name;
        this.menuModel = new LinkedList<>();
        this.userSessionBean = userSessionBean;
    }

    public String getName() {
        return name;
    }

    public List<MenuItemModel> getMenuModel() {
        return menuModel;
    }

    public UserSessionBean getUserSessionBean() {
        return userSessionBean;
    }

    public void addMenuItem(FunctionCodeEnum functionCodeEnum, FontAwesome icon) {
//        Function f = userSessionComponent.getFunction(functionCodeEnum, functionCodeEnum.getViewMode());
        Function f = userSessionBean.getFunction(functionCodeEnum);
        menuModel.add(
                new MenuItemModel(
                        null == f ? "TODO insert" : f.getMenuName(),
                        functionCodeEnum,
                        functionCodeEnum.getViewMode(),
                        icon,
                        userSessionBean.hasAccess(functionCodeEnum)
                        )
        );
    }

    public boolean isAnyActive() {
        return menuModel.stream().anyMatch((MenuItemModel o) -> (o.isAccess()));
    }


}
