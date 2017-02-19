package pl.edu.utp.view.user;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.model.security.Role;
import pl.edu.utp.model.security.User;
import pl.edu.utp.repository.RoleRepository;
import pl.edu.utp.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@SpringComponent
@UIScope
public class UserEditor extends VerticalLayout {

    private final UserRepository repository;
    private RoleRepository roleRepository;
    private User user;

    TextField name = new TextField("Name");
    TextField surname = new TextField("Surname");
    TextField age = new TextField("login");

    OptionGroup optionGroup = new OptionGroup("Roles");

    List<Role> listUpdatedRoles = new ArrayList<Role>();
    List<Role> listAllRoles = new ArrayList<Role>();
    List<Role> listUserRoles = new ArrayList<Role>();

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    @Autowired
    public UserEditor(UserRepository repository,  RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;

        listAllRoles();
        optionGroup.setMultiSelect(true);

        VerticalLayout fields = new VerticalLayout(name, surname, age, actions);
        fields.setSizeFull();
        //fields.setMargin(true);
        fields.setSpacing(true);
        actions.setSpacing(true);

        HorizontalLayout table = new HorizontalLayout(fields, optionGroup);
        table.setSizeFull();
        table.setSpacing(true);
        // actions.setMargin(true);

        addComponents(table);

        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        save.addClickListener(e -> {
            listUpdatedRoles.clear();
            for(Role role : listAllRoles){
                if(optionGroup.isSelected(role.getName())){
                    listUpdatedRoles.add(roleRepository.findByName(role.getName()));
                }
            }
            user.setRoles(listUpdatedRoles);
            repository.save(user);});

        delete.addClickListener(e -> repository.delete(user));
        cancel.addClickListener(e -> editUser(user));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editUser(User c) {
        final boolean persisted = c.getId() != null;
        if (persisted) {
            user = repository.findOne(c.getId());
            listUserRoles(user);
        }
        else {
            user = c;
        }
        cancel.setVisible(persisted);

        BeanFieldGroup.bindFieldsUnbuffered(user, this);

        setVisible(true);

        save.focus();
        name.selectAll();
    }

    public void setChangeHandler(ChangeHandler h) {
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }

    void listAllRoles() {
        listAllRoles = roleRepository.findAll();//role.getFunctions();
    }

    void listUserRoles(User user) {
        listUserRoles = user.getRoles();

        for(Role role : listAllRoles){
            optionGroup.addItem(role.getName());
            optionGroup.unselect(role.getName());

            for(Role r : listUserRoles){
                if(r.getName().equals(role.getName())){
                    optionGroup.select(r.getName());
                }
            }
        }

    }

}
