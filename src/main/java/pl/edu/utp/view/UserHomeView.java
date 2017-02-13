package pl.edu.utp.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

/**
 * Created by xxbar on 10.01.2017.
 */
@SpringView(name = UserHomeView.VIEW_NAME)
public class UserHomeView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "user-home";

    public UserHomeView() {
        init();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }

    void init() {
        setMargin(true);
        Label label = new Label("User Home View");
        label.addStyleName(ValoTheme.LABEL_LARGE);
        label.setSizeUndefined();
        addComponent(label);
    }
}
