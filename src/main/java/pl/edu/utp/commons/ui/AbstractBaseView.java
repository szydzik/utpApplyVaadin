package pl.edu.utp.commons.ui;

import com.vaadin.ui.CustomComponent;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.security.FunctionCodeEnum;
import pl.edu.utp.security.PriviledgesComponent;
import pl.edu.utp.security.UserSessionComponent;
import pl.edu.utp.security.ViewMode;

import java.io.Serializable;

/**
 * Created by szydzik on 08.02.2017.
 */
public abstract class AbstractBaseView extends CustomComponent implements Serializable {

    @Autowired
    private UserSessionComponent userSessionComponent;

    @Autowired
    private PriviledgesComponent priviledgesComponent;

    private ViewMode mode;

    public FunctionCodeEnum getFunction() {
        return FunctionCodeEnum.UNKNOWN;
    }

}
