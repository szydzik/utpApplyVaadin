package pl.edu.utp.view.user;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.ViewScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import pl.edu.utp.commons.ui.AbstractBaseView;
import pl.edu.utp.model.security.User;
import pl.edu.utp.repository.UserRepository;
import pl.edu.utp.security.FunctionCodeEnum;

import java.util.ArrayList;
import java.util.List;

@ViewScope
@SpringView(name = UserListView.VIEW_NAME)
public class UserListView extends AbstractBaseView implements View {

    public static final String VIEW_NAME = "user-list";

    private UserRepository repo;
    private UserEditor editor;
    private Grid grid;
    private TextField filter;
    private Button addNewBtn;

    @Override
    public FunctionCodeEnum getFunctionCodeEnum() {
        return FunctionCodeEnum.USER_LIST;
    }

    @Autowired
    public UserListView(UserRepository repo, UserEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid();
        this.filter = new TextField();
        this.addNewBtn = new Button("New user", FontAwesome.PLUS);

        grid.setSizeFull();
        grid.setColumns("id", "name", "surname", "login");

        filter.setInputPrompt("Filter by surname");
        filter.addTextChangeListener(e -> listUsers(e.getText()));
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            }
            else {
                editor.editUser((User) grid.getSelectedRow());
            }
        });

        addNewBtn.addClickListener(e -> editor.editUser(new User("","","","", new ArrayList<>())));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listUsers(filter.getValue());
        });

        listUsers(null);

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);

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

    void listUsers(String text) {
        List<User> list = StringUtils.isEmpty(text) ? repo.findAll() : repo.findBySurnameStartsWithIgnoreCase(text);
        grid.setContainerDataSource(new BeanItemContainer<>(User.class, list));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}

