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
public class Round implements Func {
    @Override
    public String getName() {
        return "round";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        var data = ArgsUtils.getArg(ctx, args, 0).toDouble();
        var optPrecision = ArgsUtils.getOptArg(ctx, args, 1).orElse(new Converter(0));
        var precision = optPrecision.toInt();
        // 不保留小数
        if(precision <= 0){
            return Math.round(data);
        }
        var pow = Math.pow(10, precision);
        return Math.round(data * pow) / pow;

    }

    @Override
    public String getDescription() {
        return """
                round(number, [precision])
                四舍五入
                eg.:
                    round(-1.1) => -1
                    round(1.1) => 1
                    round(1.5) => 2
                    round(3.14,1) => 3.1
                    round(3.15,1) => 3.2
                """;
    }
}
