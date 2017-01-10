package pl.edu.utp.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Label;

/**
 * Created by xxbar on 08.01.2017.
 */
@UIScope
@SpringView(name = HomeView.VIEW_NAME)
public class HomeView extends CustomComponent implements View {

    public static final String VIEW_NAME = "";

    Label text;

    public HomeView() {
        this.text= new Label();
        setCompositionRoot(new CssLayout(text));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // Get the user name from the session
        String username;
//        username = String.valueOf(getSession().getAttribute("user"));
        username = "";
        // And show the username
        text.setValue("Hello " + username);
    }
}
