package com.hyhomelab.jexpression.functions.math;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 10:34
 */
public class Power implements Func {
    @Override
    public String getName() {
        return "power";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        var num = ArgsUtils.getArg(ctx, args, 0).toBigDecimal();
        var power = ArgsUtils.getArg(ctx, args, 1).toInt();
        return num.pow(power);
    }

    @Override
    public String getDescription() {
        return """
                power(number, power)
                返回给定数字的乘幂
                @number：数值
                @power：整数幂次
                eg. power(2,3) => 8
                """;
    }
}
