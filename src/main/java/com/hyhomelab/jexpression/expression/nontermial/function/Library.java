package com.hyhomelab.jexpression.expression.nontermial.function;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/11/21 17:04
 */
public class Library {
    private final String name;
    private final Map<String, Func> functions = new HashMap<>();

    public Library(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Func> getFunctions() {
        return Arrays.asList(functions.values().toArray(new Func[0]));
    }

    public void loadFunction(Func... func) {
        if (func != null) {
            for (Func f : func) {
                functions.put(f.getName(), f);
            }
        }
    }

    public Func getFunction(String name) {
        return functions.get(name);
    }


}
