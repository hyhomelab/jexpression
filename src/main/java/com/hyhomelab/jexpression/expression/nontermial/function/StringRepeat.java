package com.hyhomelab.jexpression.expression.nontermial.function;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.utils.ArgsUtils;

public class StringRepeat implements Func{
    @Override
    public String getName() {
        return "stringRepeat";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        var str = ArgsUtils.getArg(args, 0).toString();
        var repeatTimes = ArgsUtils.getArg(args, 1).toInt();
        return String.valueOf(str).repeat(repeatTimes);
    }

    @Override
    public String getDescription() {
        return """
                返回一个重复字符串填充的字符串。
                eg. stringRepeat('abc', 3) => "abcabcabc"
                """;
    }
}
