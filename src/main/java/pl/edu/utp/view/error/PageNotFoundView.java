package pl.edu.utp.view.error;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

import javax.annotation.PostConstruct;

/**
 * Created by xxbar on 10.01.2017.
 */
@SpringComponent
@UIScope
//@SpringView(name = PageNotFoundView.VIEW_NAME)

public class PageNotFoundView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "custom-not-found";

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    @PostConstruct
    void init() {
        setMargin(true);
        Label label = new Label("Nie znaleziono widoku!");
        label.addStyleName(ValoTheme.LABEL_FAILURE);
        label.setSizeUndefined();
        addComponent(label);
    }

}
