package pl.edu.utp.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

/**
 * Created by xxbar on 10.01.2017.
 */
@UIScope
@SpringViewDisplay
public class MainLayout extends VerticalLayout implements ViewDisplay, Button.ClickListener, ViewChangeListener {

    private Panel viewContainer;
    private HorizontalLayout navbar;
    private Button btnHome;
    private Button btnUser;
    private Button btnAdmin;
    private Button btnAdminHidden;
    private Button btnSignIn;
    private Button btnSignUp;
    private Button btnLogout;

    @PostConstruct
    public void postConstuct() {
        setSizeFull();

        navbar = new HorizontalLayout();
        navbar.setWidth("100%");
        navbar.setMargin(true);
        navbar.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        addComponent(navbar);

        final Label brand = new Label("Vaadin4Spring Security Demo");
        brand.addStyleName(ValoTheme.LABEL_H2);
        brand.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        navbar.addComponent(brand);
        navbar.setComponentAlignment(brand, Alignment.MIDDLE_LEFT);
        navbar.setExpandRatio(brand, 1);

        btnHome = new Button("Home", FontAwesome.HOME);
        btnHome.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        btnHome.setData(HomeView.VIEW_NAME);
        btnHome.addClickListener(this);
        navbar.addComponent(btnHome);

        btnUser = new Button("User home", FontAwesome.USER);
        btnUser.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        btnUser.setData(UserHomeView.VIEW_NAME);
        btnUser.addClickListener(this);
        navbar.addComponent(btnUser);

        btnAdmin = new Button("Admin home", FontAwesome.USER_MD);
        btnAdmin.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        btnAdmin.setData(AdminHomeView.VIEW_NAME);
        btnAdmin.addClickListener(this);
        navbar.addComponent(btnAdmin);

        btnAdminHidden = new Button("Admin secret", FontAwesome.EYE_SLASH);
        btnAdminHidden.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        btnAdminHidden.setData(AdminSecretView.VIEW_NAME);
        btnAdminHidden.addClickListener(this);
        navbar.addComponent(btnAdminHidden);

        btnSignIn = new Button("Sign in", FontAwesome.SIGN_IN);
        btnSignIn.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        btnSignIn.setData(SimpleLoginView.VIEW_NAME);
        btnSignIn.addClickListener(this);
        navbar.addComponent(btnSignIn);

        btnSignUp = new Button("Sign up", FontAwesome.PENCIL_SQUARE_O);
        btnSignUp.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        btnSignUp.setData(RegisterView.VIEW_NAME);
        btnSignUp.addClickListener(this);
        navbar.addComponent(btnSignUp);

        btnLogout = new Button("Logout", FontAwesome.SIGN_OUT);
        btnLogout.setData("-");
        btnLogout.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        navbar.addComponent(btnLogout);
        btnLogout.addClickListener(event -> {
//                logout();
                });

        viewContainer = new Panel();
        viewContainer.setSizeFull();
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
    }

    private void logout() {
    }

    private void displayAnonymousNavbar() {
        btnAdminHidden.setVisible(false);
        btnLogout.setVisible(false);
        btnSignIn.setVisible(true);
        btnSignUp.setVisible(true);
    }

    private void displayUserNavbar() {
        btnAdminHidden.setVisible(false);
        btnLogout.setVisible(true);
        btnSignIn.setVisible(false);
        btnSignUp.setVisible(false);
    }

    private void displayAdminNavbar() {
        btnAdminHidden.setVisible(true);
        btnLogout.setVisible(true);
        btnSignIn.setVisible(false);
        btnSignUp.setVisible(false);
    }

    private void displayAllNavbar() {
        btnAdminHidden.setVisible(true);
        btnLogout.setVisible(true);
        btnSignIn.setVisible(false);
        btnSignUp.setVisible(false);
    }

    @Override
    public boolean beforeViewChange(ViewChangeEvent event) {
        return true;
    }

    @Override
    public void afterViewChange(ViewChangeEvent event) {
        for (int i=0; i<navbar.getComponentCount(); i++) {

            if (navbar.getComponent(i) instanceof Button) {
                final Button btn = (Button) navbar.getComponent(i);
                btn.removeStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
                String view = (String) btn.getData();
                if (event.getViewName().equals(view)) {
                    btn.addStyleName(ValoTheme.BUTTON_BORDERLESS_COLORED);
                }
            }
        }

    }

    @Override
    public void showView(View view) {
        displayAdminNavbar();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        UI.getCurrent().getNavigator().navigateTo((String) event.getButton().getData());
    }
}
