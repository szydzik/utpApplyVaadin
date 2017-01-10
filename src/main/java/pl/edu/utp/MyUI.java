package pl.edu.utp;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import pl.edu.utp.view.*;
import pl.edu.utp.view.error.AccessDeniedView;

/**
 * Created by xxbar on 09.01.2017.
 */
@Theme("valo")
@SpringUI
@SpringViewDisplay
public class MyUI extends UI implements ViewDisplay {

    private Panel springViewDisplay;

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout root = new VerticalLayout();
        root.setSizeFull();
        root.setMargin(true);
        root.setSpacing(true);
        setContent(root);

        final CssLayout navigationBar = new CssLayout();
        navigationBar.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);


        navigationBar.addComponent(createNavigationButton("HomePageView",
                HomePageView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("LogInView",
                LogInView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("RegisterView",
                RegisterView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("UserListView",
                UserListView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("WelcomeView",
                WelcomeView.VIEW_NAME));
        navigationBar.addComponent(createNavigationButton("AccessDeniedView",
                AccessDeniedView.VIEW_NAME));


        getNavigator().setErrorView(AccessDeniedView.class);

        root.addComponent(navigationBar);

        springViewDisplay = new Panel();
        springViewDisplay.setSizeFull();
        root.addComponent(springViewDisplay);
        root.setExpandRatio(springViewDisplay, 1.0f);

    }

    private Button createNavigationButton(String caption, final String viewName) {
        Button button = new Button(caption);
        button.addStyleName(ValoTheme.BUTTON_SMALL);
        // If you didn't choose Java 8 when creating the project, convert this
        // to an anonymous listener class
        button.addClickListener(event -> getUI().getNavigator().navigateTo(viewName));
        return button;
    }

    @Override
    public void showView(View view) {
        springViewDisplay.setContent((Component) view);
    }

}
