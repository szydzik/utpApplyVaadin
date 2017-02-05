package pl.edu.utp.security;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import pl.edu.utp.model.security.Function;
import pl.edu.utp.repository.FunctionRepository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by szydzik on 05.02.2017.
 */
@SpringComponent
@VaadinSessionScope
public class FunctionCacheBean {

    @Autowired
    FunctionRepository functionRepository;

    private Map<FunctionCode, Function> functionsCache;

    @PostConstruct
    private void postConstruct() {
        List<Function> systemFunctions = functionRepository.findAll();

        functionsCache = new HashMap<>();

        for (Function function : systemFunctions) {

            if (null != function.getFunctionEnum()) {
                FunctionCode fc = getFunctionCode(function.getCode());
                if (fc != null) {
                    functionsCache.put(getFunctionCode(function.getCode()), function);
                }else{
                    System.out.println("Brakuje enuma dla funkcji!!!");
                }
            }
        }
        System.out.println("Załadowano cache, SIZE: "+functionsCache.size());
    }

    private FunctionCode getFunctionCode(String code){
        FunctionCode functionCode = FunctionCode.valueOf(code);
        return functionCode;
    }

    public Map<FunctionCode, Function> getCache(){
        return functionsCache;
    }

}
