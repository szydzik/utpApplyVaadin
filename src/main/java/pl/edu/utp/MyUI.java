package pl.edu.utp;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import pl.edu.utp.view.*;
import pl.edu.utp.view.error.AccessDeniedView;
import pl.edu.utp.view.error.PageNotFoundView;

/**
 * Created by xxbar on 09.01.2017.
 */
@Theme("valo")
@SpringUI
@SpringViewDisplay
public class MyUI extends UI implements ViewDisplay {

    @Autowired
    SpringViewProvider viewProvider;

    @Autowired
    PageNotFoundView pageNotFoundView;

    private Panel panel;

    //buttons
    private Button btnHome;
    private Button btnUser;
    private Button btnAdmin;
    private Button btnAdminHidden;
    private Button btnSignIn;
    private Button btnSignUp;
    private Button btnLogout;

    @Override
    protected void init(VaadinRequest request) {

        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(false);
        root.setSpacing(true);
        setContent(root);

        getPage().setTitle("utpAPPLY - Vaadin");
        setErrorHandler(this::handleError);


        //-------nav-------
        final HorizontalLayout navigationBar = new HorizontalLayout();
        navigationBar.setWidth("100%");
        navigationBar.setMargin(true);
        navigationBar.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);
        final Label brand = new Label("utpAPPLY");
        brand.addStyleName(ValoTheme.LABEL_H2);
        brand.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        navigationBar.addComponent(brand);
        navigationBar.setComponentAlignment(brand, Alignment.MIDDLE_LEFT);
        navigationBar.setExpandRatio(brand, 1);


        btnHome = createNavigationButton("Home", FontAwesome.HOME, HomeView.VIEW_NAME);
        navigationBar.addComponent(btnHome);

        btnUser = createNavigationButton("User home", FontAwesome.USER, UserHomeView.VIEW_NAME);
        navigationBar.addComponent(btnUser);

        btnAdmin = createNavigationButton("Admin home", FontAwesome.USER_MD, AdminHomeView.VIEW_NAME);
        navigationBar.addComponent(btnAdmin);

        btnAdminHidden = createNavigationButton("Admin secret", FontAwesome.EYE_SLASH, AdminSecretView.VIEW_NAME);
        navigationBar.addComponent(btnAdminHidden);

        btnSignIn = createNavigationButton("Sign in", FontAwesome.SIGN_IN, SimpleLoginView.VIEW_NAME);
        navigationBar.addComponent(btnSignIn);

        btnSignUp = createNavigationButton("Sign up", FontAwesome.PENCIL_SQUARE_O,RegisterView.VIEW_NAME);
        navigationBar.addComponent(btnSignUp);

        btnLogout = createNavigationButton("Logout", FontAwesome.SIGN_OUT,HomeView.VIEW_NAME);
        navigationBar.addComponent(btnLogout);

        root.addComponent(navigationBar);

        getNavigator().addProvider(viewProvider);
        getNavigator().setErrorView(pageNotFoundView);
        viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);

        panel = new Panel();
        panel.setSizeFull();
        root.addComponent(panel);
        root.setExpandRatio(panel, 1.0f);

    }


    private Button createNavigationButton(String caption, FontAwesome fontAwesome, final String viewName) {
        Button button = new Button(caption, fontAwesome);
        button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }

    @Override
    public void showView(View view) {
        panel.setContent((Component) view);
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

    private void handleError(com.vaadin.server.ErrorEvent event) {
        Throwable t = DefaultErrorHandler.findRelevantThrowable(event.getThrowable());
        if (t instanceof AccessDeniedException) {
            Notification.show("You do not have permission to perform this operation",
                    Notification.Type.WARNING_MESSAGE);
        } else {
            DefaultErrorHandler.doDefault(event);
        }
    }

}
