package pl.edu.utp;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.edu.utp.form.SimpleLoginForm;
import pl.edu.utp.security.MenuBean;
import pl.edu.utp.security.PriviledgesBean;
import pl.edu.utp.security.SecurityUtils;
import pl.edu.utp.security.UserSessionBean;
import pl.edu.utp.view.*;
import pl.edu.utp.view.error.AccessDeniedView;
import pl.edu.utp.view.error.PageNotFoundView;

/**
 * Created by xxbar on 09.01.2017.
 */
@Theme(ValoTheme.THEME_NAME)
@SpringUI
//@Push(transport = Transport.WEBSOCKET_XHR) // Websocket would bypass the filter chain, Websocket+XHR works
//@SpringViewDisplay
public class MyUI extends UI implements ViewDisplay {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyUI.class);

//  Autowired
    private final AuthenticationManager authenticationManager;
    private final PageNotFoundView pageNotFoundView;
    private final SpringViewProvider viewProvider;
    private final HomeView homeView;
    private final UserSessionBean userSessionBean;
    private final PriviledgesBean priviledgesBean;
    private final MenuBean menuBean;
//  Autowired - end

    private Panel panel;

//  buttons
    private Button btnHome;
    private Button btnUser;
    private Button btnAdmin;
    private Button btnAdminHidden;
    private Button btnSignIn;
    private Button btnSignUp;
    private Button btnLogout;
    private Button btnUsers;
    private Button btnTest;

    @Autowired
    public MyUI(AuthenticationManager authenticationManager, PageNotFoundView pageNotFoundView, SpringViewProvider viewProvider, HomeView homeView, UserSessionBean userSessionBean, PriviledgesBean priviledgesBean, MenuBean menuBean) {
        this.panel = new Panel();
        this.authenticationManager = authenticationManager;
        this.pageNotFoundView = pageNotFoundView;
        this.viewProvider = viewProvider;
        this.homeView = homeView;
        this.userSessionBean = userSessionBean;
        this.priviledgesBean = priviledgesBean;
        this.menuBean = menuBean;
    }


    @Override
    protected void init(VaadinRequest request) {
        getPage().setTitle("Vaadin and Spring Security Demo");
        showMain();
        refreshMenu();
//        refreshPriviledges();
    }


    private void showMain() {

        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);


//        -------nav-------
//        final CssLayout navigationBar = new CssLayout();
        final HorizontalLayout navigationBar = new HorizontalLayout();
        navigationBar.setWidth("100%");
        navigationBar.setMargin(true);
        navigationBar.setSpacing(true);
        navigationBar.setDefaultComponentAlignment(Alignment.MIDDLE_RIGHT);

//        -----logo----
        final Label brand = new Label("utpAPPLY");
        brand.addStyleName(ValoTheme.LABEL_H2);
        brand.addStyleName(ValoTheme.LABEL_NO_MARGIN);
        navigationBar.addComponent(brand);
        navigationBar.setComponentAlignment(brand, Alignment.MIDDLE_LEFT);
        navigationBar.setExpandRatio(brand, 1);

//        -----add buttons-----
        btnTest = new Button("Test button", evt -> {
            LOGGER.info("==========TEST current login: {}", userSessionBean.getCurrentUser().getLogin());
            menuBean.getTabs();
//            userSessionBean.refreshUserFromContext();
        });
        navigationBar.addComponent(btnTest);

        btnHome = createNavigationButton("Home", FontAwesome.HOME, HomeView.VIEW_NAME);
        navigationBar.addComponent(btnHome);

        btnUser = createNavigationButton("User home", FontAwesome.USER, UserHomeView.VIEW_NAME);
        navigationBar.addComponent(btnUser);

        btnUsers = createNavigationButton("Users", FontAwesome.USERS, UserListView.VIEW_NAME);
        navigationBar.addComponent(btnUsers);

        btnAdmin = createNavigationButton("Admin home", FontAwesome.USER_MD, AdminHomeView.VIEW_NAME);
        navigationBar.addComponent(btnAdmin);

        btnAdminHidden = createNavigationButton("Admin secret", FontAwesome.EYE_SLASH, AdminSecretView.VIEW_NAME);
        navigationBar.addComponent(btnAdminHidden);

//        btnAccessControl = createNavigationButton("Admin Control", FontAwesome.EYE_SLASH, AccessControlView.VIEW_NAME);
//        navigationBar.addComponent(btnAccessControl);

//        btnSignIn = createNavigationButton("Sign in", FontAwesome.SIGN_IN, SimpleLoginView.VIEW_NAME);
//        navigationBar.addComponent(btnSignIn);
        btnSignIn = new Button("Sign In", evt -> {
            panel.setContent(new SimpleLoginForm(this::login));
        });
        btnSignIn.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        btnSignIn.setIcon(FontAwesome.SIGN_IN);
        navigationBar.addComponent(btnSignIn);

        btnSignUp = createNavigationButton("Sign up", FontAwesome.PENCIL_SQUARE_O,RegisterView.VIEW_NAME);
        navigationBar.addComponent(btnSignUp);

//        btnLogout = createNavigationButton("Logout", FontAwesome.SIGN_OUT,HomeView.VIEW_NAME);
//        navigationBar.addComponent(btnLogout);

        btnLogout = new Button("Logout", event -> {
            // Let Spring Security handle the logout by redirecting to the logout URL
//			getPage().setLocation("logout");
            this.logout();
        });
        btnLogout.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        navigationBar.addComponent(btnLogout);


//        getNavigator().setErrorView(PageNotFoundView.class);
        root.addComponent(navigationBar);

//        panel = new Panel();
        panel.setSizeFull();
        root.addComponent(panel);
        root.setExpandRatio(panel, 1.0f);

        setContent(root);
        setErrorHandler(this::handleError);
        getNavigator().setErrorView(pageNotFoundView);
        viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);
        getNavigator().addProvider(viewProvider);

    }


    private Button createNavigationButton(String caption, FontAwesome fontAwesome, final String viewName) {
        Button button = new Button(caption, fontAwesome);
        button.addStyleName(ValoTheme.BUTTON_BORDERLESS);
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }

    @Override
    public void showView(View view) {
        if (SecurityUtils.isLoggedIn()) {
//            System.out.println("Logged in");
            panel.setContent((Component) view);
        } else {
//            System.out.println("Not Logged in");
            panel.setContent((Component) view);
        }
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

    private void logout() {
        getPage().reload();
        getSession().close();
    }

    private boolean login(String username, String password) {
        try {
            Authentication token = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // Reinitialize the session to protect against session fixation attacks. This does not work
            // with websocket communication.
            VaadinService.reinitializeSession(VaadinService.getCurrentRequest());
            SecurityContextHolder.getContext().setAuthentication(token);
            // Now when the session is reinitialized, we can enable websocket communication. Or we could have just
            // used WEBSOCKET_XHR and skipped this step completely.
            getPushConfiguration().setTransport(Transport.WEBSOCKET);
            getPushConfiguration().setPushMode(PushMode.AUTOMATIC);
            // Show the main UI
            this.showView(homeView);

            refresh();
            return true;
        } catch (AuthenticationException ex) {
            return false;
        }
    }

    private void refreshMenu(){
        if (SecurityUtils.isLoggedIn()){
            btnSignIn.setVisible(false);
            btnSignUp.setVisible(false);
            btnLogout.setVisible(true);
        }else{
            btnSignIn.setVisible(true);
            btnSignUp.setVisible(true);
            btnLogout.setVisible(false);
        }
    }

    private void refresh(){
        refreshMenu();
        userSessionBean.refreshUserFromContext();
        menuBean.refresh();
    }

}
