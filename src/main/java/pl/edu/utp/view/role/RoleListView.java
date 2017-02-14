package pl.edu.utp.view.role;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;
import pl.edu.utp.commons.ui.AbstractBaseView;

/**
 * Created by szydzik on 14.02.2017.
 */
@ViewScope
@SpringView(name = RoleListView.VIEW_NAME)
public class RoleListView extends AbstractBaseView implements View {

    public static final String VIEW_NAME = "role-list";

    private Label label;

    public RoleListView() {
        Label label = new Label("Role List View");
        label.addStyleName(ValoTheme.LABEL_LARGE);
        label.setSizeUndefined();


        VerticalLayout viewLayout = new VerticalLayout(label);
        viewLayout.setSizeFull();
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setCompositionRoot(viewLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}
