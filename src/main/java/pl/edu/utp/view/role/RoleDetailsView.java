package pl.edu.utp.view.role;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.utp.commons.ui.AbstractBaseView;
import pl.edu.utp.security.FunctionCodeEnum;

/**
 * Created by szydzik on 14.02.2017.
 */
@ViewScope
@SpringView(name = RoleDetailsView.VIEW_NAME)
public class RoleDetailsView extends AbstractBaseView implements View {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleDetailsView.class);
    public static final String VIEW_NAME = "role-details";

    public FunctionCodeEnum getFunction() {
        return FunctionCodeEnum.ROLE_DETAILS;
    }

    public RoleDetailsView() {
        Label label = new Label("Role Details View");
        label.addStyleName(ValoTheme.LABEL_LARGE);
        label.setSizeUndefined();


        VerticalLayout viewLayout = new VerticalLayout(label);
        viewLayout.setSizeFull();
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setCompositionRoot(viewLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        LOGGER.info("===== ViewChangeEvent: {}",event);
    }

}
