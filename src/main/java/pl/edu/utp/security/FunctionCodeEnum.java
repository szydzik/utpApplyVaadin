package pl.edu.utp.security;

/**
 * Created by szydzik on 05.02.2017.
 */
public enum FunctionCodeEnum {


//    user management
    USER_LIST("USER", "user-list", ViewMode.LIST, MenuGroupEnum.ADMIN),
    USER_DETAILS("USER", "user-details", ViewMode.DETAILS, MenuGroupEnum.ADMIN),
    USER_EDIT("USER", "user-edit", ViewMode.EDIT, MenuGroupEnum.ADMIN),
    USER_CREATE("USER", "user-details", ViewMode.CREATE, MenuGroupEnum.ADMIN),
    USER_DELETE("USER", "user-delete", ViewMode.DELETE, MenuGroupEnum.ADMIN),

//    admin views
    ADMIN_HOME("ADMIN", "admin-home", ViewMode.NONE, MenuGroupEnum.ADMIN),
    ADMIN_SECRET("ADMIN", "admin-secret", ViewMode.NONE, MenuGroupEnum.ADMIN),

//    role
    ROLE_LIST("ROLE","role-list", ViewMode.LIST, MenuGroupEnum.ADMIN),
    ROLE_DETAILS("ROLE","role-details", ViewMode.DETAILS, MenuGroupEnum.ADMIN),
    ROLE_CREATE("ROLE","role-details", ViewMode.CREATE, MenuGroupEnum.ADMIN),

//    function
    FUNCTION_LIST("FUNCTION","function-list", ViewMode.LIST, MenuGroupEnum.ADMIN),
    FUNCTION_DETAILS("FUNCTION","function-details", ViewMode.DETAILS, MenuGroupEnum.ADMIN),

//    others
    HOME(null, "", ViewMode.NONE, MenuGroupEnum.HOME),
    USER_HOME(null, "user-home", ViewMode.NONE, MenuGroupEnum.USER),
//    REGISTER(null, "register", ViewMode.NONE, MenuGroupEnum.SIGN_UP),
    SIGN_IN("login"),
    UNKNOWN("unkown")
    ;

    private final String base;
    private final String view;
    private final ViewMode viewMode;
    private final MenuGroupEnum menuGroupEnum;

    FunctionCodeEnum(String base, String view, ViewMode viewMode, MenuGroupEnum menuGroupEnum) {
        this.base = base;
        this.view = view;
        this.viewMode = viewMode;
        this.menuGroupEnum = menuGroupEnum;
    }

    FunctionCodeEnum(String view) {
        this.base = null;
        this.view = view;
        this.viewMode = null;
        this.menuGroupEnum = null;
    }

    public String getView() {
        return view;
    }

    public ViewMode getViewMode() {
        return viewMode;
    }

    public MenuGroupEnum getMenuGroupEnum() {
        return menuGroupEnum;
    }

    public String getBase() {
        return base;
    }
}
