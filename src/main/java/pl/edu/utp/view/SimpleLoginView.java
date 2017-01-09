package pl.edu.utp.view;

import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.spring.annotation.UIScope;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.ValoTheme;
import pl.edu.utp.view.validator.CustomValidator;

/**
 * Created by xxbar on 08.01.2017.
 */
@UIScope
@SpringView(name = SimpleLoginView.VIEW_NAME)
public class SimpleLoginView extends CustomComponent implements View, Button.ClickListener {

    public static final String VIEW_NAME = "login";

    private final TextField email;
    private final PasswordField password;

    private final Button loginButton;
    private final Button googleButton;

    public SimpleLoginView() {
        setSizeFull();

        // Create the email input field
        email = new TextField("User:");
        email.setWidth("300px");
        email.setRequired(true);
        email.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        email.setIcon(FontAwesome.ENVELOPE);
        email.setInputPrompt("login (eg. joe@email.com)");
        email.addValidator(new EmailValidator(
                "Username must be an email address"));
        email.setInvalidAllowed(false);

        // Create the password input field
        password = new PasswordField("Password:");
        password.setWidth("300px");
        password.addValidator(new CustomValidator.PasswordValidator());
        password.setRequired(true);
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        password.setIcon(FontAwesome.LOCK);
        password.setInputPrompt("password,");
        password.setValue("");
        password.setNullRepresentation("");

        // Create login button
        loginButton = new Button("Login", this);

        googleButton = new Button("Google", this);

        // Add both to a panel
        VerticalLayout fields = new VerticalLayout(email, password, loginButton, googleButton);
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        // The view root layout
        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setCompositionRoot(viewLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // focus the username field when email arrives to the login view
        email.focus();
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {

        if (event.getButton() == googleButton){
            getUI().getNavigator().navigateTo(UserPanel.VIEW_NAME);//
        }

        //
        // Validate the fields using the navigator. By using validors for the
        // fields we reduce the amount of queries we have to use to the database
        // for wrongly entered passwords
        //
        if (!email.isValid() || !password.isValid()) {
            return;
        }

        String username = email.getValue();
        String password = this.password.getValue();

        //
        // Validate username and password with database here. For examples sake
        // I use a dummy username and password.
        //
        boolean isValid = username.equals("test@test.com")
                && password.equals("password");

        if (isValid) {

            // Store the current email in the service session
            getSession().setAttribute("email", username);

            // Navigate to main view
            getUI().getNavigator().navigateTo(SimpleLoginMainView.VIEW_NAME);//

        } else {

            // Wrong password clear the password field and refocuses it
            this.password.setValue(null);
            this.password.focus();

        }
    }
}
