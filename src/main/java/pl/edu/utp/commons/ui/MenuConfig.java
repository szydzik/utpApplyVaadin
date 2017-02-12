package pl.edu.utp.commons.ui;

import com.vaadin.server.FontAwesome;
import pl.edu.utp.model.security.Function;
import pl.edu.utp.security.FunctionCodeEnum;
import pl.edu.utp.security.MenuItemModel;
import pl.edu.utp.security.UserSessionComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szydzik on 07.02.2017.
 */
public class MenuConfig {

    private final String name;
    private final FontAwesome icon;
    private final List<MenuItemModel> menuModel;
    private final UserSessionComponent userSessionComponent;

    public MenuConfig(String name, FontAwesome icon, UserSessionComponent userSessionComponent) {
        this.name = name;
        this.icon = icon;
        this.menuModel = new ArrayList<>();
        this.userSessionComponent = userSessionComponent;
    }

    public String getName() {
        return name;
    }

    public FontAwesome getIcon() {
        return icon;
    }

    public List<MenuItemModel> getMenuModel() {
        return menuModel;
    }

    public UserSessionComponent getUserSessionComponent() {
        return userSessionComponent;
    }

    public void addMenuItem(FunctionCodeEnum functionCodeEnum) {
//        Function f = userSessionComponent.getFunction(functionCodeEnum, functionCodeEnum.getViewMode());
        Function f = userSessionComponent.getFunction(functionCodeEnum);
        menuModel.add(
                new MenuItemModel(
                        null == f ? "TODO insert" : f.getMenuName(),
                        functionCodeEnum,
                        functionCodeEnum.getViewMode(),
                        userSessionComponent.hasAccess(functionCodeEnum)
                        )
//                new Object[]{
//                        null == f ? "TODO insert" : f.getMenuName(),
//                        functionCodeEnum,
//                        functionCodeEnum.getViewMode(),
//                        userSessionComponent.hasAccess(functionCodeEnum)
//                }
        );
    }

    public boolean isAnyActive() {
        return menuModel.stream().anyMatch((MenuItemModel o) -> (o.isAccess()));
    }


}
