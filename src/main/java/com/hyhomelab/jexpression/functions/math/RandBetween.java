package com.hyhomelab.jexpression.functions.math;

import com.hyhomelab.jexpression.exception.InvalidParameterException;
import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;

import java.util.Random;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/18 17:16
 */
public class RandBetween implements Func {
    @Override
    public String getName() {
        return "randBetween";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        if (args.length == 0) {
            throw new InvalidParameterException(String.format("[%s]参数不能为空", this.getName()));
        }
        var min = ArgsUtils.getArg(ctx, args, 0);
        var intMin = min.toInt();
        var max = ArgsUtils.getArg(ctx, args, 1);
        var intMax = max.toInt();

        return this.calc(new Random().nextDouble(), intMin, intMax);
    }

    public int calc(double rand, int min, int max) {
        return (int)Math.floor(rand * (max - min + 1) + min);
    }

    @Override
    public String getDescription() {
        return """
                randBetween(min, max)
                返回大于等于min，小于等于max的随机数
                eg. randBetween(1, 3) => 1
                """;
    }
}
