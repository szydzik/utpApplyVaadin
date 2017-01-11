package pl.edu.utp.view;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.model.Person;
import pl.edu.utp.repository.PersonRepository;

@SpringComponent
@UIScope
public class PersonEditor extends VerticalLayout {

    private final PersonRepository repository;
    private Person person;

    TextField name = new TextField("Name");
    TextField surname = new TextField("Last Name");
    TextField age = new TextField("Age");
    TextField dateOfBirth = new TextField("Date Birth");

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public PersonEditor(PersonRepository repository) {
        this.repository = repository;

        addComponents(name, surname, age, dateOfBirth, actions);

        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        save.addClickListener(e -> repository.save(person));
        delete.addClickListener(e -> repository.delete(person));
        cancel.addClickListener(e -> editCustomer(person));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editCustomer(Person c) {
        final boolean persisted = c.getId() != null;
        if (persisted) {
            person = repository.findOne(c.getId());
        }
        else {
            person = c;
        }
        cancel.setVisible(persisted);

        BeanFieldGroup.bindFieldsUnbuffered(person, this);

        setVisible(true);

        save.focus();
        name.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }

}
