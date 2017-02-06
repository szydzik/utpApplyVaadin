package pl.edu.utp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityUtils {

    private SecurityUtils() {
    }

    public static boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("========== Authentication:"+authentication);
        if(authentication != null) {
            return authentication.isAuthenticated();
        }else {
            return false;
        }
    }

    public static boolean hasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("==========  Authentication:"+authentication);
        return authentication != null && authentication.getAuthorities().contains(new SimpleGrantedAuthority(role));
    }

}
