package com.hyhomelab.jexpression.expression.nontermial.function;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.utils.ArgsUtils;

public class Not implements Func{
    @Override
    public String getName() {
        return "not";
    }

    @Override
    public Object call(Context ctx, Object... args) {
        boolean arg = ArgsUtils.getArg(args, 0).toBoolean();
        return !arg;
    }

    @Override
    public String getDescription() {
        return """
                取反
                eg. not(1<2) => false
                """;
    }
}
