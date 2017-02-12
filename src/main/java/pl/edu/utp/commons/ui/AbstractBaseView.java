package pl.edu.utp.commons.ui;

import com.vaadin.ui.CustomComponent;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.security.FunctionCodeEnum;
import pl.edu.utp.security.PriviledgesComponent;
import pl.edu.utp.security.UserSessionComponent;
import pl.edu.utp.security.ViewMode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by szydzik on 08.02.2017.
 */
public abstract class AbstractBaseView extends CustomComponent implements Serializable {

    @Autowired
    private UserSessionComponent userSessionComponent;

    @Autowired
    private PriviledgesComponent priviledgesComponent;

    private ViewMode mode;

    public UserSessionComponent getUserSessionComponent() {
        return userSessionComponent;
    }

    public void setUserSessionComponent(UserSessionComponent userSessionComponent) {
        this.userSessionComponent = userSessionComponent;
    }

    public PriviledgesComponent getPriviledgesComponent() {
        return priviledgesComponent;
    }

    public void setPriviledgesComponent(PriviledgesComponent priviledgesComponent) {
        this.priviledgesComponent = priviledgesComponent;
    }

    public ViewMode getMode() {
        return mode;
    }

    public void setMode(ViewMode mode) {
        this.mode = mode;
    }

    public List<FunctionCodeEnum> getAvailavleFunctionCodeEnums() {
        return Arrays.asList(getFunctionCodeEnum());
    }

    public FunctionCodeEnum getFunctionCodeEnum() {
        return FunctionCodeEnum.UNKNOWN;
    }

    public String getBaseFunctionCodeEnum() {
        return null;
    }

}
