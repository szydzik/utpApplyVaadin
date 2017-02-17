package pl.edu.utp.security;

/**
 * Created by szydzik on 05.02.2017.
 */
public enum FunctionCodeEnum {


//    user management
    USER_LIST("USER", "user-list", ViewMode.LIST),
    USER_DETAILS("USER", "user-details", ViewMode.DETAILS),
    USER_EDIT("USER", "user-edit", ViewMode.EDIT),
    USER_CREATE("USER", "user-details", ViewMode.CREATE),
    USER_DELETE("USER", "user-delete", ViewMode.DELETE),

//    admin views
    ADMIN_HOME("ADMIN", "admin-home", ViewMode.NONE),
    ADMIN_SECRET("ADMIN", "admin-secret", ViewMode.NONE),

//    role
    ROLE_LIST("ROLE","role-list", ViewMode.LIST),
    ROLE_DETAILS("ROLE","role-details", ViewMode.DETAILS),
    ROLE_CREATE("ROLE","role-details", ViewMode.CREATE),

//    function
    FUNCTION_LIST("FUNCTION","function-list", ViewMode.LIST),
    FUNCTION_DETAILS("FUNCTION","function-details", ViewMode.DETAILS),

//    others
    HOME(null, "", ViewMode.NONE),
    USER_HOME(null, "user-home", ViewMode.NONE),
//    REGISTER(null, "register", ViewMode.NONE, MenuGroupEnum.SIGN_UP),
    SIGN_IN("login"),
    UNKNOWN("unkown")
    ;

    private final String base;
    private final String view;
    private final ViewMode viewMode;

    FunctionCodeEnum(String base, String view, ViewMode viewMode) {
        this.base = base;
        this.view = view;
        this.viewMode = viewMode;
    }

    FunctionCodeEnum(String view) {
        this.base = null;
        this.view = view;
        this.viewMode = null;
    }

    public String getView() {
        return view;
    }

    public ViewMode getViewMode() {
        return viewMode;
    }

    public String getBase() {
        return base;
    }
}
