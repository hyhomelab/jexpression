package com.hyhomelab.jexpression.expression.nontermial.function;

import com.hyhomelab.jexpression.exception.InvalidParameterException;
import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Expression;

import java.math.BigDecimal;


public class Sum implements Func {
    @Override
    public String getName() {
        return "sum";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        if (args.length == 0) {
            throw new InvalidParameterException(String.format("[%s]参数不能为空", this.getName()));
        }
        BigDecimal result = BigDecimal.ZERO;
        for (Object arg : args) {
            if (arg instanceof Expression) {
                var argsResult = ((Expression) arg).interpret(ctx);
                if (!(argsResult instanceof Number)) {
                    throw new InvalidParameterException(String.format("[%s]参数值不是数字", this.getName()));
                }
                result = result.add(new BigDecimal(argsResult.toString()));
            } else {
                result = result.add(new BigDecimal(arg.toString()));
            }
        }
        return result;
    }

    @Override
    public String getDescription() {
        return "累加器，使用方法sum(number1, number2....)";
    }

}
