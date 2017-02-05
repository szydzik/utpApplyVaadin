package pl.edu.utp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.logging.Logger;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            System.out.println("Authentication class: " + authentication.getClass().getName());
            System.out.println("Authentication:"+authentication);
            return authentication.isAuthenticated();
        }else {
            System.out.println("Authentication:"+authentication);
            return false;
        }
    }

    public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("========authentication:"+authentication);
        return authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority(role));
    }
}
