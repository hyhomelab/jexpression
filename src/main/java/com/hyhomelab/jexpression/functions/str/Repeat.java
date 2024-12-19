package com.hyhomelab.jexpression.functions.str;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;

public class Repeat implements Func {
    @Override
    public String getName() {
        return "repeat";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        var str = ArgsUtils.getArg(ctx, args, 0).toString();
        var repeatTimes = ArgsUtils.getArg(ctx, args, 1).toInt();
        return String.valueOf(str).repeat(repeatTimes);
    }

    @Override
    public String getDescription() {
        return """
                repeat(str, times)
                返回一个重复字符串填充的字符串。
                eg. repeat('abc', 3) => "abcabcabc"
                """;
    }
}
