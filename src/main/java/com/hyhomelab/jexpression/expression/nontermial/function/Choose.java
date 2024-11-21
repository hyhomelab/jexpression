package com.hyhomelab.jexpression.expression.nontermial.function;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.utils.ArgsUtils;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/11/21 11:19
 */
public class Choose implements Func{
    @Override
    public String getName() {
        return "choose";
    }

    @Override
    public Object call(Context ctx, Object... args) {

        for(var i=0;i<args.length - 1;i+=2){
            var condition = ArgsUtils.getArg(ctx, args, i).toBoolean();
            var trueValue = ArgsUtils.getArg(ctx, args, i+1).getValue();
            if(condition){
                return trueValue;
            }
        }
        return ArgsUtils.getArg(ctx, args, args.length-1).getValue();
    }

    @Override
    public String getDescription() {
        return """
                choose 方法
                使用方法:choose(condition1, matchValue1, condition2, matchValue2,....elseValue)
                eg: choose(gpa==4,'A', gpa==3, 'B', gpa==2,'C', gpa==1, 'D', 'F')
                """;
    }
}
