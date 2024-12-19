package com.hyhomelab.jexpression.functions.math;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 10:34
 */
public class Abs implements Func {
    @Override
    public String getName() {
        return "abs";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        var data = ArgsUtils.getArg(ctx, args, 0).toBigDecimal();
        return data.abs();
    }

    @Override
    public String getDescription() {
        return """
                abs(number)
                返回给定数值的绝对值
                eg. abs(-1)
                """;
    }
}
