/*
 * Copyright 2000-2014 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package pl.edu.utp;

import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.navigator.ViewDisplay;
import com.vaadin.server.*;
import com.vaadin.shared.communication.PushMode;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import pl.edu.utp.commons.ui.MenuConfig;
import pl.edu.utp.form.SimpleLoginForm;
import pl.edu.utp.security.MenuBean;
import pl.edu.utp.security.MenuItemModel;
import pl.edu.utp.security.UserSessionBean;
import pl.edu.utp.utils.StringGenerator;
import pl.edu.utp.view.HomeView;
import pl.edu.utp.view.error.AccessDeniedView;
import pl.edu.utp.view.error.PageNotFoundView;

import java.util.Iterator;

@Theme("valo")
@Title("Valo Theme Test")
@StyleSheet("valo-theme-ui.css")
@PreserveOnRefresh
@SpringUI
@SpringViewDisplay
public class ValoThemeUI2 extends UI implements ViewDisplay {

    //  Autowired
    private final AuthenticationManager authenticationManager;
    private final PageNotFoundView pageNotFoundView;
    private final SpringViewProvider viewProvider;
    private final HomeView homeView;
    private final UserSessionBean userSessionBean;
    private final MenuBean menuBean;
//  Autowired - end

    private boolean responsive = false;

    private ValoMenuLayout root = new ValoMenuLayout();
    private Panel panel = root.getContentContainer();
    private CssLayout menu = new CssLayout();
    private CssLayout menuItemsLayout = new CssLayout();
    {
        menu.setId("testMenu");
    }

    @Autowired
    public ValoThemeUI2(AuthenticationManager authenticationManager, PageNotFoundView pageNotFoundView, SpringViewProvider viewProvider, HomeView homeView, UserSessionBean userSessionBean, MenuBean menuBean) {
        this.authenticationManager = authenticationManager;
        this.pageNotFoundView = pageNotFoundView;
        this.viewProvider = viewProvider;
        this.homeView = homeView;
        this.userSessionBean = userSessionBean;
        this.menuBean = menuBean;
    }

    @Override
    protected void init(VaadinRequest request) {
        if (request.getParameter("test") != null) {
            responsive = false;

            if (browserCantRenderFontsConsistently()) {
                getPage().getStyles().add(
                        ".v-app.v-app.v-app {font-family: Sans-Serif;}");
            }
        }

        if (getPage().getWebBrowser().isIE()
                && getPage().getWebBrowser().getBrowserMajorVersion() == 9) {
            menu.setWidth("320px");
        }

        if (responsive) {
            Responsive.makeResponsive(this);
        }

        getPage().setTitle("utpApply - title");
        setContent(root);
        root.setWidth("100%");

        root.addMenu(buildMenu());
        addStyleName(ValoTheme.UI_WITH_MENU);

        getNavigator().addProvider(viewProvider);
        getNavigator().setErrorView(pageNotFoundView);
        viewProvider.setAccessDeniedViewClass(AccessDeniedView.class);

        getNavigator().addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {
                for (Iterator<Component> it = menuItemsLayout.iterator(); it
                        .hasNext();) {
                    it.next().removeStyleName("selected");
                }

                for (MenuItemModel i : menuBean.getAllMenuItems()){
                    if (event.getViewName().equals(i.getFuncionCodeEnum().getView())) {
                        for (Iterator<Component> it = menuItemsLayout.iterator(); it.hasNext();) {
                            Component c = it.next();
                            if (c.getCaption() != null && c.getCaption().startsWith(i.getTitle())) {
                                c.addStyleName("selected");
                                break;
                            }
                        }
                        break;
                    }
                }
                menu.removeStyleName("valo-menu-visible");
            }
        });

    }


    private boolean browserCantRenderFontsConsistently() {
        // PhantomJS renders font correctly about 50% of the time, so
        // disable it to have consistent screenshots
        // https://github.com/ariya/phantomjs/issues/10592

        // IE8 also has randomness in its font rendering...

        return getPage().getWebBrowser().getBrowserApplication()
                .contains("PhantomJS")
                || (getPage().getWebBrowser().isIE() && getPage()
                .getWebBrowser().getBrowserMajorVersion() <= 9);
    }

    private CssLayout buildMenu() {

        HorizontalLayout top = new HorizontalLayout();
        top.setWidth("100%");
        top.setDefaultComponentAlignment(Alignment.MIDDLE_LEFT);
        top.addStyleName(ValoTheme.MENU_TITLE);
        menu.addComponent(top);

        Button showMenu = new Button("Menu", (Button.ClickListener) event -> {
            if (menu.getStyleName().contains("valo-menu-visible")) {
                menu.removeStyleName("valo-menu-visible");
            } else {
                menu.addStyleName("valo-menu-visible");
            }
        });
        showMenu.addStyleName(ValoTheme.BUTTON_PRIMARY);
        showMenu.addStyleName(ValoTheme.BUTTON_SMALL);
        showMenu.addStyleName("valo-menu-toggle");
        showMenu.setIcon(FontAwesome.LIST);
        menu.addComponent(showMenu);

        Label title = new Label("<h3>Vaadin <strong>utpApply</strong></h3>",
                ContentMode.HTML);
        title.setSizeUndefined();
        top.addComponent(title);
        top.setExpandRatio(title, 1);

        MenuBar settings = new MenuBar();
        settings.addStyleName("user-menu");
        StringGenerator sg = new StringGenerator();
        MenuBar.MenuItem settingsItem = settings.addItem(sg.nextString(true)
                        + " " + sg.nextString(true) + sg.nextString(false),
                new ClassResource("profile-pic-300px.jpg"),
                null);
        settingsItem.addItem("Edit Profile", null);
        settingsItem.addItem("Preferences", null);
        settingsItem.addSeparator();
        settingsItem.addItem("Sign Out", null);
        menu.addComponent(settings);

        {
            Button btnSignIn = new Button("Sign In", evt -> {
                panel.setContent(new SimpleLoginForm(this::login));
            });
            btnSignIn.setIcon(FontAwesome.SIGN_IN);
            btnSignIn.setHtmlContentAllowed(true);
            btnSignIn.setPrimaryStyleName(ValoTheme.MENU_ITEM);
            menuItemsLayout.addComponent(btnSignIn);

            Button btnLogout = new Button("Logout", event -> {
                this.logout();
            });
            btnLogout.setIcon(FontAwesome.SIGN_OUT);
            btnLogout.setPrimaryStyleName(ValoTheme.MENU_ITEM);
            btnSignIn.setHtmlContentAllowed(true);
            menuItemsLayout.addComponent(btnLogout);
        }


        menuItemsLayout.setPrimaryStyleName("valo-menuitems");
        menu.addComponent(menuItemsLayout);

        {
            Label label = null;
            for ( MenuConfig tab : menuBean.getTabs()){
                label = new Label(tab.getName(), ContentMode.HTML);
                label.setPrimaryStyleName(ValoTheme.MENU_SUBTITLE);
                label.addStyleName(ValoTheme.LABEL_H4);
                label.setSizeUndefined();
                menuItemsLayout.addComponent(label);

                tab.getMenuModel().forEach(item -> {
                    Button b = new Button(item.getTitle(), (Button.ClickListener) event -> getNavigator().navigateTo(item.getFuncionCodeEnum().getView()));
                    b.setHtmlContentAllowed(true);
                    b.setPrimaryStyleName(ValoTheme.MENU_ITEM);
                    b.setIcon(item.getIcon());
//                    b.setIcon(FontAwesome.HOME);
                    menuItemsLayout.addComponent(b);
                });

            }
            label.setValue(label.getValue() + " <span class=\"valo-menu-badge\">"
                    + "test" + "</span>");
        }
        return menu;
    }

    @Override
    public void showView(View view) {
        panel.setContent((Component) view);
    }

    private void logout() {
        getNavigator().navigateTo("");
        getPage().reload();
        getSession().close();
    }

    private boolean login(String username, String password) {
        try {
            Authentication token = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // Reinitialize the session to protect against session fixation attacks. This does not work
            // with websocket communication.
            VaadinService.reinitializeSession(VaadinService.getCurrentRequest());
            SecurityContextHolder.getContext().setAuthentication(token);
            // Now when the session is reinitialized, we can enable websocket communication. Or we could have just
            // used WEBSOCKET_XHR and skipped this step completely.
            getPushConfiguration().setTransport(Transport.WEBSOCKET);
            getPushConfiguration().setPushMode(PushMode.AUTOMATIC);
            // Show the main UI
            this.showView(homeView);

            refresh();
            return true;
        } catch (AuthenticationException ex) {
            return false;
        }
    }

    private void handleError(com.vaadin.server.ErrorEvent event) {
        Throwable t = DefaultErrorHandler.findRelevantThrowable(event.getThrowable());
        if (t instanceof AccessDeniedException) {
            Notification.show("You do not have permission to perform this operation",
                    Notification.Type.WARNING_MESSAGE);
        } else {
            DefaultErrorHandler.doDefault(event);
        }
    }

    private void refresh(){
        userSessionBean.refresh();
        menuBean.refresh();
    }
}
