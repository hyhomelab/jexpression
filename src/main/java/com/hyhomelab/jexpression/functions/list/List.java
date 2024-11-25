package com.hyhomelab.jexpression.functions.list;

import com.hyhomelab.jexpression.exception.InvalidParameterException;
import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;

import java.util.ArrayList;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/11/25 17:26
 */
public class List implements Func {
    @Override
    public String getName() {
        return "list";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        if (args == null || args.length == 0) {
            throw new InvalidParameterException("参数不能为空");
        }
        var Objects = new ArrayList<Object>();
        for (var i=0; i<args.length; i++) {
            Objects.add(ArgsUtils.getArg(ctx, args, i).getValue());
        }
        return Objects;
    }

    @Override
    public String getDescription() {
        return """
                构建列表
                使用方式：list(arg1, arg2, arg3)
                eg. list(1,2,3)
                """;
    }
}
