package pl.edu.utp.view.error;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;

/**
 * Created by xxbar on 10.01.2017.
 */
@UIScope
@SpringView(name = AccessDeniedView.VIEW_NAME)
public class AccessDeniedView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "access-denied";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        addComponent(new Label("MyError: Access Denied"));
    }
}
