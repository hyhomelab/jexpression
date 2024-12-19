package com.hyhomelab.jexpression.functions.math;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 10:34
 */
public class Round implements Func {
    @Override
    public String getName() {
        return "round";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        var data = ArgsUtils.getArg(ctx, args, 0).toDouble();
        return (int)Math.round(data);
    }

    @Override
    public String getDescription() {
        return """
                round(number)
                四舍五入
                eg:
                    ceil(-1.1) => -1
                    ceil(1.1) => 1
                    ceil(1.5) => 2
                """;
    }
}
