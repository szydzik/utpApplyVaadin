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

    private Map<FunctionCodeEnum, Map<ViewMode, Function>> functionsCache;

    @PostConstruct
    private void postConstruct() {
        System.out.println("===============================================================================");
        System.out.println("===== Rozpoczynam ładowanie cache funkcji");
        List<Function> systemFunctions = functionRepository.findAll();

        functionsCache = new HashMap<>();

        for (Function function : systemFunctions) {
            if (null != function.getFunctionEnum()) {
//                functionsCache.put(getFunctionCode(function.getFunctionEnum()), function);
                Object[] functionArray = getFunctionArray(function.getFunctionEnum());
                addToCache(
                        null == functionArray[0] ? FunctionCodeEnum.valueOf(function.getFunctionEnum()) : (FunctionCodeEnum) functionArray[0],
                        (ViewMode) functionArray[1],
                        function
                );
            }else{
//            TODO dodać własny wyjątek
//                System.out.println("===== ERROR: Funkcja "+ function.getCode() +" nie posiada nazwy enuma!!!");
                throw new UnsupportedOperationException("===== ERROR: Funkcja "+ function.getCode() +" nie posiada nazwy enuma!!!");
            }
        }
        System.out.println("=====  Załadowano cache, SIZE: "+functionsCache.size());
        System.out.println("===============================================================================");
    }

    private FunctionCodeEnum getFunctionCode(String code){
        FunctionCodeEnum functionCodeEnum = FunctionCodeEnum.valueOf(code);
        return functionCodeEnum;
    }

    public Map<FunctionCodeEnum, Map<ViewMode, Function>> getCache(){
        return functionsCache;
    }

    public Function getFunction(FunctionCodeEnum action, ViewMode viewMode){
        if (functionsCache.get(action) != null) {
            return functionsCache.get(action).get(viewMode);
        }
        return null;
    }

    /**
     * Funkcja zwraca obiekt Object[]{FunctionCodeEnum, ViewMode} na podstawie functionEnum z funkcji
     * @param functionEnum
     * @return
     */
    public Object[] getFunctionArray(String functionEnum) {
        ViewMode viewMode;
        FunctionCodeEnum functionCodeEnum = null;

        if (stringEndsWithAny(functionEnum, "_LIST")) {
            viewMode = ViewMode.LIST;
            functionCodeEnum = FunctionCodeEnum.valueOf(functionEnum);
        } else if (stringEndsWithAny(functionEnum, "_DETAILS", "_CREATE", "_EDIT", "_DELETE")) {

            String stringViewMode = functionEnum.substring(functionEnum.lastIndexOf("_") + 1, functionEnum.length());
            String functionCode = functionEnum.substring(0, functionEnum.lastIndexOf("_"));

            viewMode = ViewMode.valueOf(stringViewMode);
            functionCodeEnum = FunctionCodeEnum.valueOf(functionCode);

        } else {
            viewMode = ViewMode.NONE;
            functionCodeEnum = FunctionCodeEnum.valueOf(functionEnum);
        }
        return new Object[]{functionCodeEnum, viewMode};
    }

    private boolean stringEndsWithAny(String toCheck, String... endings) {
        for (String end : endings) {
            if (toCheck.endsWith(end)) {
                return true;
            }
        }
        return false;
    }

    private void addToCache(FunctionCodeEnum functionCodeTO, ViewMode viewMode, Function function) {
        if (null == functionsCache.get(functionCodeTO)) {
            functionsCache.put(functionCodeTO, new HashMap<>());
        }
        functionsCache.get(functionCodeTO).put(viewMode, function);
    }

}
