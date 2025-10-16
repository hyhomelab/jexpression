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
public class Floor implements Func {
    @Override
    public String getName() {
        return "floor";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        var data = ArgsUtils.getArg(ctx, args, 0).toDouble();
        var optPrecision = ArgsUtils.getOptArg(ctx, args, 1).orElse(new Converter(0));
        var precision = optPrecision.toInt();
        // 不保留小数
        if(precision <= 0){
            return (long)Math.floor(data);
        }
        var pow = Math.pow(10, precision);
        return Math.floor(data * pow) / pow;
    }

    @Override
    public String getDescription() {
        return """
                floor(number, [precision])
                向下取整
                eg.:
                    floor(-1.1) => -1
                    floor(1.1) => 2
                    floor(3.14,1) => 3.1
                    floor(3.15,1) => 3.1
                """;
    }
}
