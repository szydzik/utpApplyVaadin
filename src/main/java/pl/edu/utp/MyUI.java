package pl.edu.utp;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.view.MainLayout;

/**
 * Created by xxbar on 09.01.2017.
 */
@Theme("valo")
@SpringUI
//@SpringViewDisplay
public class MyUI extends UI {//implements ViewDisplay {

    @Autowired
    MainLayout mainLayout;

    @Autowired
    SpringViewProvider springViewProvider;

//    private Panel panel;

//    @Override
//    protected void init(VaadinRequest request) {
//
//        final VerticalLayout root = new VerticalLayout();
//        root.setSizeFull();
//        root.setMargin(true);
//        root.setSpacing(true);
//        setContent(root);
//
//        final CssLayout navigationBar = new CssLayout();
//        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
//
//        navigationBar.addComponent(createNavigationButton("HomeView",
//                HomeView.VIEW_NAME));
//        navigationBar.addComponent(createNavigationButton("UserHomeView",
//                UserHomeView.VIEW_NAME));
//        navigationBar.addComponent(createNavigationButton("AdminHomeView",
//                AdminHomeView.VIEW_NAME));
//        navigationBar.addComponent(createNavigationButton("AdminSecretView",
//                AdminSecretView.VIEW_NAME));
//        navigationBar.addComponent(createNavigationButton("LogInView",
//                SimpleLoginView.VIEW_NAME));
//
//        //Log Out button and nav
//        Button button = new Button("Log Out");
//        button.addStyleName(ValoTheme.BUTTON_SMALL);
//        button.addClickListener(event -> {
//            getUI().getNavigator().navigateTo(HomeView.VIEW_NAME);
//        });
//        navigationBar.addComponent(button);
//
////        navigationBar.addComponent(createNavigationButton("RegisterView",
////                RegisterView.VIEW_NAME));
//
//        getNavigator().setErrorView(CustomNotFoundView.class);
//
//        root.addComponent(navigationBar);
//
//        panel = new Panel();
//        panel.setSizeFull();
//        root.addComponent(panel);
//        root.setExpandRatio(panel, 1.0f);
//
//    }

    @Override
    protected void init(VaadinRequest request) {
//        setLocale(new Locale.Builder().setLanguage("sr").setScript("Latn").setRegion("RS").build());
//        SecuredNavigator securedNavigator = new SecuredNavigator(MainUI.this, mainLayout, springViewProvider, security, eventBus);
//        securedNavigator.addViewChangeListener(mainLayout);
//        setContent(mainLayout);
//        setErrorHandler(new SpringSecurityErrorHandler());
//        Navigator navigator = new Navigator(this,mainLayout);

//        new Navigator(this,(ViewDisplay) mainLayout);
        getNavigator().addViewChangeListener(mainLayout);
        getNavigator().addProvider(springViewProvider);
        setContent(mainLayout);
    }

//    private Button createNavigationButton(String caption, final String viewName) {
//        Button button = new Button(caption);
//        button.addStyleName(ValoTheme.BUTTON_SMALL);
//        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
//        return button;
//    }

//    @Override
//    public void showView(View view) {
//        panel.setContent((Component) view);
//    }

}
