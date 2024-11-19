package com.hyhomelab.jexpression.expression.nontermial.function;

import com.hyhomelab.jexpression.expression.Context;

public interface Func {
    String getName();
    Object call(Context ctx, Object... args);
    String getDescription();
}
