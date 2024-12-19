package com.hyhomelab.jexpression.functions.math;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 10:34
 */
public class Pi implements Func {
    @Override
    public String getName() {
        return "pi";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        return Math.PI;
    }

    @Override
    public String getDescription() {
        return """
                pi()
                数学常量 pi,返回3.14159265358979323846
                """;
    }
}
