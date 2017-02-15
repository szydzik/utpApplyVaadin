package pl.edu.utp.view.function;

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
import pl.edu.utp.model.security.Function;
import pl.edu.utp.repository.FunctionRepository;

/**
 * Created by Tomala on 2017-02-15.
 */
@SpringComponent
@UIScope
public class FunctionEditor extends VerticalLayout {

    private final FunctionRepository repository;
    private Function function;

    TextField code = new TextField("Code");
    TextField description = new TextField("Description");
    TextField function_enum = new TextField("Function");
    TextField menuGroup = new TextField("Menu Group");
    TextField menuName = new TextField("Menu Name");
    TextField name = new TextField("Name");
//    TextField dateOfBirth = new TextField("Date Birth");

    /* Action buttons */
    Button save = new Button("Save", FontAwesome.SAVE);
    Button cancel = new Button("Cancel");
    Button delete = new Button("Delete", FontAwesome.TRASH_O);
    CssLayout actions = new CssLayout(save, cancel, delete);

    @Autowired
    public FunctionEditor(FunctionRepository repository) {
        this.repository = repository;

        addComponents(code,description,function_enum,menuGroup,menuName,name,actions);

        setSpacing(true);
        actions.setStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);

        save.addClickListener(e -> repository.save(function));
        delete.addClickListener(e -> repository.delete(function));
        cancel.addClickListener(e -> editFunction(function));
        setVisible(false);
    }

    public interface ChangeHandler {

        void onChange();
    }

    public final void editFunction(Function c) {
        final boolean persisted = c.getId() != null;
        if (persisted) {
            function = repository.findOne(c.getId());
        }
        else {
            function = c;
        }
        cancel.setVisible(persisted);

        BeanFieldGroup.bindFieldsUnbuffered(function, this);

        setVisible(true);

        save.focus();
        code.selectAll();
    }

    public void setChangeHandler(pl.edu.utp.view.function.FunctionEditor.ChangeHandler h) {
        save.addClickListener(e -> h.onChange());
        delete.addClickListener(e -> h.onChange());
    }

}
