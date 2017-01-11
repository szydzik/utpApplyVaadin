package pl.edu.utp.view;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import pl.edu.utp.model.Person;
import pl.edu.utp.repository.PersonRepository;

import java.util.Date;

@UIScope
@SpringView(name = PersonView.VIEW_NAME)
public class PersonView extends CustomComponent implements View {

    public static final String VIEW_NAME = "users";

    private PersonRepository repo;
    private PersonEditor editor;
    private Grid grid;
    private TextField filter;
    private Button addNewBtn;

    @Autowired
    public PersonView(PersonRepository repo, PersonEditor editor) {
        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid();
        this.filter = new TextField();
        this.addNewBtn = new Button("New user", FontAwesome.PLUS);

        grid.setHeight(300, Unit.PIXELS);
        grid.setColumns("id", "name", "surname", "age", "dateOfBirth");

        filter.setInputPrompt("Filter by last name");
        filter.addTextChangeListener(e -> listUsers(e.getText()));
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            }
            else {
                editor.editCustomer((Person) grid.getSelectedRow());
            }
        });

        addNewBtn.addClickListener(e -> editor.editCustomer(new Person("", "", 1, new Date())));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listUsers(filter.getValue());
        });

        listUsers(null);

        HorizontalLayout actions = new HorizontalLayout(filter, addNewBtn);

        VerticalLayout fields = new VerticalLayout(actions, grid, editor);
        fields.setSizeUndefined();
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
        if (StringUtils.isEmpty(text)) {
            grid.setContainerDataSource(
                    new BeanItemContainer(Person.class, repo.findAll()));
        }
        else {
            grid.setContainerDataSource(new BeanItemContainer(Person.class,
                    repo.findBySurnameStartsWithIgnoreCase(text)
            ));
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }
}

