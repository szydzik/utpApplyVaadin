package pl.edu.utp.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

/**
 * Created by xxbar on 08.01.2017.
 */
@UIScope
@SpringView(name = SimpleLoginMainView.VIEW_NAME)
public class SimpleLoginMainView extends CustomComponent implements View {

    public static final String VIEW_NAME = "simple-login";

    Label text = new Label();

    Button logout = new Button("Logout", new Button.ClickListener() {

        @Override
        public void buttonClick(Button.ClickEvent event) {

            // "Logout" the user
            getSession().setAttribute("user", null);

            // Refresh this view, should redirect to login view
            getUI().getNavigator().navigateTo(LogInView.VIEW_NAME);
        }
    });

    public SimpleLoginMainView() {
        setCompositionRoot(new CssLayout(text, logout));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // Get the user name from the session
        String username = String.valueOf(getSession().getAttribute("user"));

        // And show the username
        text.setValue("Hello " + username);
    }
}
