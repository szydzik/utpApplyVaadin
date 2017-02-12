package pl.edu.utp.security;

/**
 * Created by szydzik on 05.02.2017.
 */
public enum FunctionCodeEnum {


//    user management
    USER_LIST("USER", "user-list", ViewMode.LIST, MenuGroup.ADMIN),
    USER_DETAILS("USER", "user-details", ViewMode.DETAILS, MenuGroup.ADMIN),
    USER_EDIT("USER", "user-edit", ViewMode.EDIT, MenuGroup.ADMIN),
    USER_CREATE("USER", "user-create", ViewMode.CREATE, MenuGroup.ADMIN),
    USER_DELETE("USER", "user-delete", ViewMode.DELETE, MenuGroup.ADMIN),

//    admin views
    ADMIN_HOME("ADMIN", "admin-home", ViewMode.NONE, MenuGroup.ADMIN),
    ADMIN_SECRET("ADMIN", "admin-secret", ViewMode.NONE, MenuGroup.ADMIN),

//    others
    HOME(null, "", ViewMode.NONE, MenuGroup.HOME),
    USER_HOME(null, "user-home", ViewMode.NONE, MenuGroup.USER),
    REGISTER(null, "register", ViewMode.NONE, MenuGroup.SIGN_UP),
    SIGN_IN("login"),
    UNKNOWN("unkown")
    ;

    private final String base;
    private final String view;
    private final ViewMode viewMode;
    private final MenuGroup menuGroup;

    FunctionCodeEnum(String base, String view, ViewMode viewMode, MenuGroup menuGroup) {
        this.base = base;
        this.view = view;
        this.viewMode = viewMode;
        this.menuGroup = menuGroup;
    }

    FunctionCodeEnum(String view) {
        this.base = null;
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
