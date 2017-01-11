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

    private Label label;

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        label.setValue(String.format("No such view: %s", event.getViewName()));
    }

    @PostConstruct
    void init() {
        setMargin(true);
        label = new Label();
        label.addStyleName(ValoTheme.LABEL_FAILURE);
        label.setSizeUndefined();
        addComponent(label);
    }

}
