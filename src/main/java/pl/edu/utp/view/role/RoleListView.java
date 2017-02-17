package pl.edu.utp.view.role;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import pl.edu.utp.commons.ui.AbstractBaseView;
import pl.edu.utp.model.security.Role;
import pl.edu.utp.repository.RoleRepository;
import pl.edu.utp.security.FunctionCodeEnum;
import pl.edu.utp.security.PriviledgesBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szydzik on 14.02.2017.
 */
@ViewScope
@SpringView(name = RoleListView.VIEW_NAME)
public class RoleListView extends AbstractBaseView implements View {

    public static final String VIEW_NAME = "role-list";

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleListView.class);

    @Override
    public FunctionCodeEnum getFunction() {
        return FunctionCodeEnum.ROLE_LIST;
    }

    private RoleRepository repo;
    private PriviledgesBean priviledgesBean;
    private RoleEditor editor;
    private Grid grid;
    private TextField filter;
    private Button addNewBtn;
    private Button showBtn;

    @Autowired
    public RoleListView(RoleRepository repo, RoleEditor editor, PriviledgesBean priviledgesBean) {
        this.repo = repo;
        this.editor = editor;
        this.priviledgesBean = priviledgesBean;
        this.grid = new Grid();
        this.filter = new TextField();
        this.addNewBtn = new Button("New role", FontAwesome.PLUS);
        this.showBtn = new Button("Show role id = 101", (e) -> {
            showRole();
        });

        grid.setSizeFull();
        grid.setColumns("id", "name");

        filter.setInputPrompt("Filter by code");
        filter.addTextChangeListener(e -> listRoles(e.getText()));
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            }
            else {
                editor.editRole((Role) grid.getSelectedRow());
            }
        });

        addNewBtn.addClickListener(e -> editor.editRole(new Role("", new ArrayList<>(), new ArrayList<>())));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listRoles(filter.getValue());
        });

        listRoles(null);

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn, showBtn);

        VerticalLayout fields = new VerticalLayout(actions, grid, editor);
        fields.setSizeFull();
        actions.setSpacing(true);
        fields.setMargin(true);
        fields.setSpacing(true);

        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setCompositionRoot(viewLayout);
    }

    void listRoles(String text) {
        List<Role> list = StringUtils.isEmpty(text) ? repo.findAll() : repo.findByNameStartsWithIgnoreCase(text);
        grid.setContainerDataSource(new BeanItemContainer<>(Role.class, list));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

    public void showRole(){
        getUI().getNavigator().navigateTo(RoleDetailsView.VIEW_NAME+"/id=101");
    }

    public void changePage(Long itemId, FunctionCodeEnum outcome) {
        // jeśli mamy uprawnienia to zmieniamy stronę
        if (priviledgesBean.hasPriviledges(outcome)) {
//            changePage(itemId, outcome);
        }
    }

}
