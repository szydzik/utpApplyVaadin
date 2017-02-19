package pl.edu.utp.view.role;

import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.server.FontAwesome;
import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.model.security.Function;
import pl.edu.utp.model.security.Role;
import pl.edu.utp.repository.FunctionRepository;
import pl.edu.utp.repository.RoleRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomala on 2017-02-15.
 */
@SpringComponent
@UIScope
public class RoleEditor extends VerticalLayout {
    private final RoleRepository repository;
    private Role role;
    private FunctionRepository functionRepository;

    List<Function> listUpdatedFunctions = new ArrayList<Function>();
    List<Function> listAllFunctions = new ArrayList<Function>();
    List<Function> listUserFunctions = new ArrayList<Function>();
    OptionGroup optionGroup = new OptionGroup("Functions");

    TextField name = new TextField("Name");

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    HorizontalLayout actions = new HorizontalLayout(save, cancel, delete);

    @Autowired
    public RoleEditor(RoleRepository repository, FunctionRepository functionRepository) {
        this.repository = repository;
        this.functionRepository = functionRepository;

        listAllFunctions();
        optionGroup.setMultiSelect(true);

        VerticalLayout fields = new VerticalLayout(name, actions);
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
            listUpdatedFunctions.clear();
            for(Function function : listAllFunctions){
                if(optionGroup.isSelected(function.getName())){
                    listUpdatedFunctions.add(functionRepository.findByName(function.getName()));
                }
            }
            role.setFunctions(listUpdatedFunctions);
            repository.save(role);});
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
            listUserFunctions(role);
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

    void listAllFunctions() {
        listAllFunctions = functionRepository.findAll();
    }

    void listUserFunctions(Role role) {
        listUserFunctions = role.getFunctions();

        for(Function function : listAllFunctions){
            optionGroup.addItem(function.getName());
            optionGroup.unselect(function.getName());

            for(Function f : listUserFunctions){
                if(f.getName().equals(function.getName())){
                    optionGroup.select(f.getName());
                }
            }
        }

    }

}
