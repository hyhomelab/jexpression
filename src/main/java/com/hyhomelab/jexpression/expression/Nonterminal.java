package com.hyhomelab.jexpression.expression;

public abstract class Nonterminal implements Expression {
    @Override
    public ExpressionType getType() {
        return ExpressionType.NONTERMINAL;
    }

}
