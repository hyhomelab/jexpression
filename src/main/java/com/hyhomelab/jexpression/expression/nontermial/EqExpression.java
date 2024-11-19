package com.hyhomelab.jexpression.expression.nontermial;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Expression;
import com.hyhomelab.jexpression.expression.Nonterminal;

import java.util.Objects;

public class EqExpression extends Nonterminal {

    private final Expression left;
    private final Expression right;

    public EqExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;

    }

    @Override
    public Object interpret(Context ctx) {
        var leftResult = left.interpret(ctx);
        var rightResult = right.interpret(ctx);
        return leftResult.equals(rightResult);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EqExpression that = (EqExpression) o;
        return Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
