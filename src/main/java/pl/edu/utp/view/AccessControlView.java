package pl.edu.utp.view;

import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * This demonstrates how you can control access to views.
 */
//@VaadinSessionScope
//@SpringView(name = AccessControlView.VIEW_NAME)
public class AccessControlView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "access";

    private final Set<String> allowedViews = new HashSet<>();

    @PostConstruct
    void init() {

        setMargin(true);
        setSpacing(true);
        addComponent(new Label("Here you can control the access to the different views within this particular UI. Uncheck a few boxes and try to navigate to their corresponding views. " +
                "In a real application, you would probably base the access decision on the current user's role or something similar."));

    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    private CheckBox createViewCheckbox(String caption, final String viewName) {
        final CheckBox checkBox = new CheckBox(caption, true);
        checkBox.addValueChangeListener((Property.ValueChangeListener) event -> {
            if (checkBox.getValue()) {
                allowedViews.add(viewName);
            } else {
                allowedViews.remove(viewName);
            }
        });
        return checkBox;
    }

}
