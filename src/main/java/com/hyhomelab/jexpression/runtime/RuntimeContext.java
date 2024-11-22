package com.hyhomelab.jexpression.runtime;

import com.hyhomelab.jexpression.exception.FunctionNotFoundException;
import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class RuntimeContext implements Context {
    private final Context parentContext;
    private final Map<String, Object> variables = new HashMap<>();
    private FuncFinder funcFinder;

    public RuntimeContext() {
        this.parentContext = null;
    }

    public RuntimeContext(Context ctx) {
        this.parentContext = ctx;
    }

    public void setFuncFinder(FuncFinder funcFinder) {
        this.funcFinder = funcFinder;
    }

    @Override
    public Func getFunc(String funcName) {
        if (funcFinder == null) {
            throw new FunctionNotFoundException(String.format("Function [%s] not found", funcName));
        }
        return funcFinder.getFunc(funcName);
    }

    @Override
    public Object getVar(String name) {
        var val = this.variables.get(name);
        if (val != null) {
            return val;
        }
        if(parentContext != null){
            return parentContext.getVar(name);
        }
        return null;
    }

    @Override
    public void setVar(String name, Object value) {
        // 数值全部包装成 BigDecimal
        if (value instanceof Number && !(value instanceof BigDecimal)) {
            value = new BigDecimal(value.toString());
        }
        this.variables.put(name, value);
    }
}
