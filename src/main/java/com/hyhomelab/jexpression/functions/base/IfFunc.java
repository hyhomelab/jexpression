package com.hyhomelab.jexpression.functions.base;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/11/21 11:06
 */
public class IfFunc implements Func {
    @Override
    public String getName() {
        return "if";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        var condition = ArgsUtils.getArg(ctx, args, 0).toBoolean();
        var trueValue = ArgsUtils.getArg(ctx, args, 1).getValue();
        var falseValue = ArgsUtils.getArg(ctx, args, 2).getValue();
        return condition ? trueValue : falseValue;
    }

    @Override
    public String getDescription() {
        return """
                if 方法
                使用方法if(condition, trueValue, falseValue)
                eg: if(age>=18, 'adult', 'child')
                """;
    }
}
