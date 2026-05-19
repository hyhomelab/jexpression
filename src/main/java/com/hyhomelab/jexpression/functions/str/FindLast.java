package com.hyhomelab.jexpression.functions.str;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.utils.ArgsUtils;
import com.hyhomelab.jexpression.utils.Converter;

import java.math.BigDecimal;

public class FindLast implements Func {
    @Override
    public String getName() {
        return "findLast";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        var findStr = ArgsUtils.getArg(ctx, args, 0).toString();
        var allText = ArgsUtils.getArg(ctx, args, 1).toString();
        var startPos = ArgsUtils.getOptArg(ctx, args, 2).orElse(new Converter(allText.length())).toInt();
        return new BigDecimal(allText.lastIndexOf(findStr, startPos));
    }

    @Override
    public String getDescription() {
        return """
                findLast(find_text, text, [start_pos])
                返回一个字符串在另外一个字符串中的最后一次出现起始位置，不存在返回-1
                eg. findLast('c', 'abc') => 2
                """;
    }
}
