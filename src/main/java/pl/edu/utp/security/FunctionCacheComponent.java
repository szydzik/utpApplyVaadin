package pl.edu.utp.security;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.VaadinSessionScope;
import org.springframework.beans.factory.annotation.Autowired;
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
public class FunctionCacheComponent {

    @Autowired
    FunctionRepository functionRepository;

    private Map<FunctionCodeEnum, Function> functionsCache;

    @PostConstruct
    private void postConstruct() {
        System.out.println("===============================================================================");
        System.out.println("===== Rozpoczynam ładowanie cache funkcji");
        List<Function> systemFunctions = functionRepository.findAll();

        functionsCache = new HashMap<>();

        for (Function function : systemFunctions) {
            if (null != function.getFunctionEnum()) {
                functionsCache.put(getFunctionCode(function.getFunctionEnum()), function);
            }else{
                System.out.println("===== ERROR: Funkcja "+ function.getCode() +" nie posiada enuma!!!");
            }
        }
        System.out.println("=====  Załadowano cache, SIZE: "+functionsCache.size());
        System.out.println("===============================================================================");
    }

    private FunctionCodeEnum getFunctionCode(String code){
        FunctionCodeEnum functionCodeEnum = FunctionCodeEnum.valueOf(code);
        return functionCodeEnum;
    }

    public Map<FunctionCodeEnum, Function> getCache(){
        return functionsCache;
    }

}
