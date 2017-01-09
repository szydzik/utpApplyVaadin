package pl.edu.utp.view;

import com.google.api.services.plus.model.Person;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.oauth.CurrentUser;

import java.util.Optional;

//@SpringUI(path = "/uu")
@UIScope
@SpringView(name = UserPanel.VIEW_NAME)
public class UserPanel extends UI implements View{

    public static final String VIEW_NAME = "uu";

    @Autowired
    CurrentUser currentUser;

    @Override
    protected void init(VaadinRequest request) {
        Optional<Person> profile = currentUser.getProfile();
        VerticalLayout layout = new VerticalLayout();
        layout.setSpacing(true);
        layout.setMargin(true);
        setContent(layout);
        if (!profile.isPresent()) {
            layout.addComponent(new Label("No profile available"));
        } else {
            Person p = profile.get();
            if (p.getImage() != null) {
                Image photo = new Image();
                photo.setSource(new ExternalResource(p.getImage().getUrl()));
                layout.addComponent(photo);
            }
            layout.addComponent(new Label(p.getDisplayName()));
        }
        Button logout = new Button("Logout", evt -> {
            getSession().getSession().invalidate();
            getPage().reload();
        });
        layout.addComponent(logout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // Get the user name from the session
//        String username = String.valueOf(getSession().getAttribute("user"));

        // And show the username
        //text.setValue("Hello " + username);
    }
}

