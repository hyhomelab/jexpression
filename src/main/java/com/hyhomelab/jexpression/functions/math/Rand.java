package com.hyhomelab.jexpression.functions.math;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;

import java.util.Random;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/18 17:16
 */
public class Rand implements Func {
    @Override
    public String getName() {
        return "rand";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        return new Random().nextDouble();
    }

    @Override
    public String getDescription() {
        return """
                rand()
                返回大于等于0，小于1的随机数
                eg. rand() => 0.7750041852434486
                """;
    }
}
