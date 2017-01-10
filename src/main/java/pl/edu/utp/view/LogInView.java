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
import pl.edu.utp.validator.CustomValidator;

/**
 * Created by xxbar on 08.01.2017.
 */
@UIScope
@SpringView(name = LogInView.VIEW_NAME)
public class LogInView extends CustomComponent implements View {

    public static final String VIEW_NAME = "login";

    private final TextField email;
    private final PasswordField password;

    private final Button loginButton;
    private final Button facebookButton;
    private final Button googleButton;

    public LogInView() {
        setSizeFull();

        // Create the email input field
        email = new TextField("User:");
        email.setWidth("300px");
        email.setRequired(true);
        email.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);
        email.setIcon(FontAwesome.ENVELOPE);
        email.setInputPrompt("Email");
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
        password.setInputPrompt("Password");
//        password.setValue("");
        password.setNullRepresentation("");

        // Create login button
        loginButton = new Button("Login");
        loginButton.setWidth("300px");
        loginButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
        loginButton.addClickListener(event -> {
            if (!email.isValid() || !password.isValid()) {
                return;
            }
            String username = email.getValue();
            String password = this.password.getValue();
            boolean isValid = username.equals("test@test.com") && password.equals("password");
            if (isValid) {
                // Store the current email in the service session
                getSession().setAttribute("email", username);
//                getUI().getNavigator().navigateTo(.VIEW_NAME);//
            }
        });

        facebookButton = new Button("Log in with Facebook", FontAwesome.FACEBOOK);
        facebookButton.setWidth("300px");
        facebookButton.setStyleName(ValoTheme.BUTTON_PRIMARY);
        facebookButton.addClickListener(event -> {

        });

        googleButton = new Button("Log in with Google", FontAwesome.GOOGLE);
        googleButton.setWidth("300px");
//        googleButton.setStyleName(ValoTheme.Button_);
        googleButton.addClickListener(event -> {});

        // Add both to a panel
        VerticalLayout fields = new VerticalLayout(email, password, loginButton, facebookButton, googleButton);
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
    }

}
