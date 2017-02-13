package pl.edu.utp.security;

/**
 * Created by xxbar on 05.02.2017.
 */
public enum MenuGroupEnum {
    HOME("Home"),
    ADMIN("Admin"),
    LOGIN,
    SIGN_IN,
    SIGN_UP,
    USER("User");

    private final String name;

    MenuGroupEnum(String name) {
        this.name = name;
    }

    MenuGroupEnum() {
        this.name = null;
    }
}
