package com.hyhomelab.jexpression.expression;

public interface Expression {
    Object interpret(Context ctx);
    ExpressionType getType();
}
