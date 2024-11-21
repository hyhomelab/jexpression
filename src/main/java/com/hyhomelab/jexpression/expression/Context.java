package com.hyhomelab.jexpression.expression;

import com.hyhomelab.jexpression.expression.nontermial.function.Func;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class Context {

    private final Map<String, Object> variables = new HashMap<>();
    private Map<String, Func> functions = new HashMap<>();

    public void addFunc(Func func) {
        this.functions.put(func.getName(), func);
    }

    public void setFuncMap(Map<String, Func> functions ) {
        this.functions = functions;
    }

    public Func getFunc(String funcName) {
        return functions.get(funcName);
    }

    public Object getVar(String name) {
        return this.variables.get(name);
    }

    public void setVar(String name, Object value) {
        // 数值全部包装成 BigDecimal
        if (value instanceof Number && !(value instanceof BigDecimal)) {
            value = new BigDecimal(value.toString());
        }
        this.variables.put(name, value);
    }
}
