package com.hyhomelab.jexpression.functions.math;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/18 15:34
 */
public class Max implements Func {
    @Override
    public String getName() {
        return "max";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("max参数不能为空");
        }
        var maxData = ArgsUtils.getArg(ctx, args, 0);
        for(int i=1;i<args.length;i++){
            var data = ArgsUtils.getArg(ctx, args, i);
            if (!(data.getValue() instanceof Number)){
                throw new IllegalArgumentException("max的参数必须都是数值或是执行结果是数值");
            }
            maxData = maxData.toBigDecimal().compareTo(data.toBigDecimal()) > 0 ? maxData : data;
        }
        return maxData.toBigDecimal();
    }

    @Override
    public String getDescription() {
        return """
                max(number1, number2, ....)
                返回参数中的最大值
                eg: max(1,2,3) => 3
                """;
    }
}
