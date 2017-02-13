package pl.edu.utp.security;

import com.vaadin.server.FontAwesome;

/**
 * Created by szydzik on 08.02.2017.
 */
public class MenuItemModel {

    private String title;
    private FunctionCodeEnum funcionCodeEnum;
    private ViewMode viewMode;
    private final FontAwesome icon;
    private boolean access;

    public MenuItemModel(String title, FunctionCodeEnum funcionCodeEnum, ViewMode viewMode, FontAwesome icon, boolean access) {
        this.title = title;
        this.funcionCodeEnum = funcionCodeEnum;
        this.viewMode = viewMode;
        this.icon = icon;
        this.access = access;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FunctionCodeEnum getFuncionCodeEnum() {
        return funcionCodeEnum;
    }

    public void setFuncionCodeEnum(FunctionCodeEnum funcionCodeEnum) {
        this.funcionCodeEnum = funcionCodeEnum;
    }

    public ViewMode getViewMode() {
        return viewMode;
    }

    public void setViewMode(ViewMode viewMode) {
        this.viewMode = viewMode;
    }

    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }

    public FontAwesome getIcon() {
        return icon;
    }
}
