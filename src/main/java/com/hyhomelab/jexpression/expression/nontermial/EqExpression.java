package com.hyhomelab.jexpression.expression.nontermial;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Expression;
import com.hyhomelab.jexpression.expression.Nonterminal;

import java.math.BigDecimal;
import java.util.Objects;

public class EqExpression extends Nonterminal {

    private final Expression left;
    private final Expression right;

    public EqExpression(Expression left, Expression right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("args cannot be null");
        }
        this.left = left;
        this.right = right;

    }

    @Override
    public Object interpret(Context ctx) {
        var leftResult = left.interpret(ctx);
        var rightResult = right.interpret(ctx);
        if(leftResult instanceof BigDecimal && rightResult instanceof BigDecimal) {
            return ((BigDecimal) leftResult).compareTo((BigDecimal) rightResult) == 0;
        }
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
