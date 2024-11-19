package com.hyhomelab.jexpression.expression.terminal;

import com.hyhomelab.jexpression.exception.VarNotFoundException;
import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Terminal;

import java.util.Objects;

public class VarExpression extends Terminal {

    private final String varName;

    public VarExpression(String varName) {
        this.varName = varName;
    }

    @Override
    public Object interpret(Context ctx) {
        var data = ctx.getVar(this.varName);
        if(data == null){
            throw new VarNotFoundException(this.varName);
        }
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VarExpression that = (VarExpression) o;
        return Objects.equals(varName, that.varName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(varName);
    }
}
