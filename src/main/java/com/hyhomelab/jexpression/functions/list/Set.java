package com.hyhomelab.jexpression.functions.list;

import com.hyhomelab.jexpression.exception.InvalidParameterException;
import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;

import java.util.Collection;
import java.util.HashSet;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/11/25 17:26
 */
public class Set implements Func {
    @Override
    public String getName() {
        return "set";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        if (args == null || args.length == 0) {
            throw new InvalidParameterException("参数不能为空");
        }
        var Objects = new HashSet<>();
        for (var i=0; i<args.length; i++) {
            var arg = ArgsUtils.getArg(ctx, args, i).getValue();
            if(arg instanceof Collection<?> collection){
                Objects.addAll(collection);
            }else{
                Objects.add(arg);
            }
        }
        return Objects.stream().toList();
    }

    @Override
    public String getDescription() {
        return """
                set(arg1, arg2, arg3...)
                构建去重的列表
                eg. list(1,2,3)
                """;
    }
}
