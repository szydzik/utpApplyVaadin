package pl.edu.utp.access;

import com.vaadin.spring.access.ViewAccessControl;
import com.vaadin.ui.UI;

/**
 * This demonstrates how you can control access to views.
 */
//@Component
public class SampleViewAccessControl implements ViewAccessControl {

    @Override
    public boolean isAccessGranted(UI ui, String beanName) {
        System.out.println("======Bean name: "+beanName);
        if (beanName.equals("adminSecretView")) {
            return SecurityUtils.hasRole("ROLE_ADMIN");
        } else {
            return SecurityUtils.hasRole("ROLE_USER");
        }
    }
}
