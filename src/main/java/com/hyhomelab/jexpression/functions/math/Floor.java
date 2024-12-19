package com.hyhomelab.jexpression.functions.math;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 10:34
 */
public class Floor implements Func {
    @Override
    public String getName() {
        return "floor";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        var data = ArgsUtils.getArg(ctx, args, 0).toDouble();
        return (int)Math.floor(data);
    }

    @Override
    public String getDescription() {
        return """
                floor(number)
                向下取整
                eg. floor(-1.1) => -2，floor(1.1) => 1
                """;
    }
}
