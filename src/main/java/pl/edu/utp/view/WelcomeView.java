package pl.edu.utp.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by xxbar on 10.01.2017.
 */
@SpringView(name = WelcomeView.VIEW_NAME)
public class WelcomeView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "welcome";


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    @PostConstruct
    void init() {
        addComponent(new Label("Widok: "+VIEW_NAME));
    }
}