package pl.edu.utp.view.role;

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
import pl.edu.utp.model.security.Role;
import pl.edu.utp.repository.RoleRepository;

/**
 * Created by Tomala on 2017-02-15.
 */
@SpringComponent
@UIScope
public class RoleEditor extends VerticalLayout {
    private final RoleRepository repository;
    private Role role;

    TextField name = new TextField("Name");

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public RoleEditor(RoleRepository repository) {
        this.repository = repository;

        addComponents(name,actions);

        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        save.addClickListener(e -> repository.save(role));
        delete.addClickListener(e -> repository.delete(role));
        cancel.addClickListener(e -> editRole(role));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editRole(Role c) {
        final boolean persisted = c.getId() != null;
        if (persisted) {
            role = repository.findOne(c.getId());
        }
        else {
            role = c;
        }
        cancel.setVisible(persisted);

        BeanFieldGroup.bindFieldsUnbuffered(role, this);

        setVisible(true);

        save.focus();
        name.selectAll();
    }

    public void setChangeHandler(pl.edu.utp.view.role.RoleEditor.ChangeHandler h) {
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }

}
