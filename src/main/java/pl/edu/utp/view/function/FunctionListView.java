package pl.edu.utp.view.function;

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
import pl.edu.utp.model.security.Function;
import pl.edu.utp.repository.FunctionRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by szydzik on 14.02.2017.
 */
@ViewScope
@SpringView(name = FunctionListView.VIEW_NAME)
public class FunctionListView extends AbstractBaseView implements View {

    public static final String VIEW_NAME = "function-list";

    private FunctionRepository repo;
    private FunctionEditor editor;
    private Grid grid;
    private TextField filter;
    private Button addNewBtn;


    @Autowired
    public FunctionListView(FunctionRepository repo, FunctionEditor editor) {

        this.repo = repo;
        this.editor = editor;
        this.grid = new Grid();
        this.filter = new TextField();
        this.addNewBtn = new Button("New function", FontAwesome.PLUS);

        grid.setSizeFull();
        grid.setColumns("id", "code", "name", "description", "functionEnum","menuName","menuGroup");

        filter.setInputPrompt("Filter by code");
        filter.addTextChangeListener(e -> listFunctions(e.getText()));
        grid.addSelectionListener(e -> {
            if (e.getSelected().isEmpty()) {
                editor.setVisible(false);
            }
            else {
                editor.editFunction((Function) grid.getSelectedRow());
            }
        });

        addNewBtn.addClickListener(e -> editor.editFunction(new Function("","","","","", true, "",  new ArrayList<>())));

        editor.setChangeHandler(() -> {
            editor.setVisible(false);
            listFunctions(filter.getValue());
        });

        listFunctions(null);

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

    void listFunctions(String text) {
        List<Function> list = StringUtils.isEmpty(text) ? repo.findAll() : repo.findByCodeStartsWithIgnoreCase(text);
        grid.setContainerDataSource(new BeanItemContainer<>(Function.class, list));
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {

    }

}
