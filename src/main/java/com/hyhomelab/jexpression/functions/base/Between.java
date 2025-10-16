package com.hyhomelab.jexpression.functions.base;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/11/21 11:19
 */
public class Between implements Func {
    @Override
    public String getName() {
        return "between";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        var val = ArgsUtils.getArg(ctx, args, 0).toBigDecimal();
        var min = ArgsUtils.getArg(ctx, args, 1).toBigDecimal();
        var max = ArgsUtils.getArg(ctx, args, 2).toBigDecimal();
        return val.compareTo(min) >= 0 && val.compareTo(max) <= 0;
    }

    @Override
    public String getDescription() {
        return """
                between(val, min, max)
                判断val是否在[min,max]范围内
                eg:  between(2, 1,3)
                """;
    }
}
