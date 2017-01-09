package pl.edu.utp.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Created by xxbar on 08.01.2017.
 */
@SpringView(name = ViewScopedView.VIEW_NAME)
public class ViewScopedView extends VerticalLayout implements View {

    public static final String VIEW_NAME = "view";

    // A new instance will be created for every view instance created
    @Autowired
    private ViewGreeter viewGreeter;

    // The same instance will be used by all views of the UI
    @Autowired
    private Greeter uiGreeter;

    @PostConstruct
    void init() {
        setMargin(true);
        setSpacing(true);
        addComponent(new Label("This is a view scoped view"));
        addComponent(new Label(uiGreeter.sayHello()));
        addComponent(new Label(viewGreeter.sayHello()));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // This view is constructed in the init() method()
    }
}