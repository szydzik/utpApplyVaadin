package pl.edu.utp.security;

/**
 * Created by szydzik on 05.02.2017.
 */
public enum FunctionCodeEnum {

    HOME("", ViewMode.NONE, MenuGroup.HOME),
    USER_HOME("user-home", ViewMode.NONE, MenuGroup.USER),


    USER("user-list", ViewMode.LIST, MenuGroup.ADMIN),
    USER_DETAILS("user-details", ViewMode.DETAILS, MenuGroup.ADMIN),
    USER_EDIT("user-edit", ViewMode.EDIT, MenuGroup.ADMIN),
    USER_CREATE("user-create", ViewMode.CREATE, MenuGroup.ADMIN),
    USER_DELETE("user-delete", ViewMode.DELETE, MenuGroup.ADMIN),

    REGISTER("register", ViewMode.NONE, MenuGroup.SIGN_UP),

    ADMIN_HOME("admin-home", ViewMode.NONE, MenuGroup.ADMIN),
    ADMIN_SECRET("admin-secret", ViewMode.NONE, MenuGroup.ADMIN)
    ;


    private final String view;
    private final ViewMode viewMode;
    private final MenuGroup menuGroup;

    private FunctionCodeEnum() {
        view = null;
        viewMode = null;
        menuGroup = null;
    }

    FunctionCodeEnum(String view, ViewMode viewMode, MenuGroup menuGroup) {
        this.view = view;
        this.viewMode = viewMode;
        this.menuGroup = menuGroup;
    }

    private FunctionCodeEnum(String view) {
        this.view = view;
        this.viewMode = null;
        this.menuGroup = null;
    }

    public String getView() {
        return view;
    }

    public ViewMode getViewMode() {
        return viewMode;
    }

    public MenuGroup getMenuGroup() {
        return menuGroup;
    }
}
