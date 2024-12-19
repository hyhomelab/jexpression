package com.hyhomelab.jexpression.utils;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Expression;

public class ArgsUtils {
    /**
     * 获取参数
     * @param ctx 上下文
     * @param args 参数列表
     * @param index 参数位置
     * @return 表达式执行结果
     */
    public static Converter getArg(Context ctx, Object[] args, int index) {
        if (args == null || index < 0 || index >= args.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + args.length);
        }
        var arg = args[index];
        if (arg instanceof Expression){
            var result = ((Expression) arg).interpret(ctx);
            return new Converter(result);
        }
        return new Converter(arg);
    }

}
