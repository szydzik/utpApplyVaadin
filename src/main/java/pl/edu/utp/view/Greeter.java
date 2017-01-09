package pl.edu.utp.view;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;

/**
 * Created by xxbar on 08.01.2017.
 */
@SpringComponent
@UIScope
public class Greeter {

    public String sayHello() {
        return "Hello from bean " + toString();
    }

}
