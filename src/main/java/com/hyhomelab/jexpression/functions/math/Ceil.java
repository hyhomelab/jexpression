package com.hyhomelab.jexpression.functions.math;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;
import com.hyhomelab.jexpression.utils.Converter;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 10:34
 */
public class Ceil implements Func {
    @Override
    public String getName() {
        return "ceil";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        var data = ArgsUtils.getArg(ctx, args, 0).toDouble();
        var optPrecision = ArgsUtils.getOptArg(ctx, args, 1).orElse(new Converter(0));
        var precision = optPrecision.toInt();
        // 不保留小数
        if(precision <= 0){
            return (long)Math.ceil(data);
        }
        var pow = Math.pow(10, precision);
        return Math.ceil(data * pow) / pow;
    }

    @Override
    public String getDescription() {
        return """
                ceil(number, [precision])
                向上取整
                eg.:
                    ceil(-1.1) => -1
                    ceil(1.1) => 2
                    ceil(3.14,1) => 3.2
                    ceil(3.15,1) => 3.2
                """;
    }
}
