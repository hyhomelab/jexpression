package com.hyhomelab.jexpression.expression;

public abstract class Terminal implements Expression {
    @Override
    public ExpressionType getType() {
        return ExpressionType.TERMINAL;
    }
}
