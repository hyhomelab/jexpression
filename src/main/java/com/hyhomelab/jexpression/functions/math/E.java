package com.hyhomelab.jexpression.functions.math;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 10:34
 */
public class E implements Func {
    @Override
    public String getName() {
        return "e";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        return Math.E;
    }

    @Override
    public String getDescription() {
        return """
                e()
                数学常量e，返回2.7182818284590452354
                """;
    }
}
