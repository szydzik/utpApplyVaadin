package pl.edu.utp.security;

/**
 * Created by szydzik on 05.02.2017.
 */
public enum FunctionCode {

    HOME("", ViewMode.NONE, MenuGroup.HOME),
    USER("user-details", ViewMode.DETAILS, MenuGroup.ADMIN),
    USER_EDIT("user-edit", ViewMode.EDIT, MenuGroup.ADMIN),
    USER_CREATE("user-create", ViewMode.CREATE, MenuGroup.ADMIN),
    USER_LIST("user-list", ViewMode.LIST, MenuGroup.ADMIN),
    USER_DELETE("user-delete", ViewMode.DELETE, MenuGroup.ADMIN),
    ADMIN_HOME("admin-home", ViewMode.NONE, MenuGroup.ADMIN),
    ADMIN_SECRET("admin-secret", ViewMode.NONE, MenuGroup.ADMIN)
    ;


    private final String view;
    private final ViewMode viewMode;
    private final MenuGroup menuGroup;

    private FunctionCode() {
        view = null;
        viewMode = null;
        menuGroup = null;
    }

    FunctionCode(String view, ViewMode viewMode, MenuGroup menuGroup) {
        this.view = view;
        this.viewMode = viewMode;
        this.menuGroup = menuGroup;
    }

    private FunctionCode(String view) {
        this.view = view;
        this.viewMode = null;
        this.menuGroup = null;
    }
}
