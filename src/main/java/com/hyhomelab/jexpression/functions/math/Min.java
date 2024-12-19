package com.hyhomelab.jexpression.functions.math;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/18 15:34
 */
public class Min implements Func {
    @Override
    public String getName() {
        return "min";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("min参数不能为空");
        }
        var min = ArgsUtils.getArg(ctx, args, 0);
        for(int i=1;i<args.length;i++){
            var data = ArgsUtils.getArg(ctx, args, i);
            if (!(data.getValue() instanceof Number)){
                throw new IllegalArgumentException("min的参数必须都是数值或是执行结果是数值");
            }
            min = min.toBigDecimal().compareTo(data.toBigDecimal()) < 0 ? min : data;
        }
        return min.toBigDecimal();
    }

    @Override
    public String getDescription() {
        return """
                min(number1, number2, ....)
                返回参数中的最小值
                eg: min(1,2,3) => 1
                """;
    }
}
