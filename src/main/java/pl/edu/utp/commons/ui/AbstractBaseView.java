package pl.edu.utp.commons.ui;

import com.vaadin.ui.CustomComponent;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.security.FunctionCodeEnum;
import pl.edu.utp.security.PriviledgesBean;
import pl.edu.utp.security.UserSessionBean;
import pl.edu.utp.security.ViewMode;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Created by szydzik on 08.02.2017.
 */
public abstract class AbstractBaseView extends CustomComponent implements Serializable {

    @Autowired
    private UserSessionBean userSessionBean;

    @Autowired
    private PriviledgesBean priviledgesBean;

    private ViewMode mode;

    public UserSessionBean getUserSessionBean() {
        return userSessionBean;
    }

    public void setUserSessionBean(UserSessionBean userSessionBean) {
        this.userSessionBean = userSessionBean;
    }

    public PriviledgesBean getPriviledgesBean() {
        return priviledgesBean;
    }

    public void setPriviledgesBean(PriviledgesBean priviledgesBean) {
        this.priviledgesBean = priviledgesBean;
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
