package com.hyhomelab.jexpression.expression.nontermial;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Expression;
import com.hyhomelab.jexpression.expression.Nonterminal;

import java.math.BigDecimal;
import java.util.Objects;

public class GeExpression extends Nonterminal {

    private final Expression left;
    private final Expression right;

    public GeExpression(Expression left, Expression right) {
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
        if (leftResult instanceof Number && rightResult instanceof Number){
            var result = new BigDecimal(leftResult.toString()).compareTo(new BigDecimal(rightResult.toString()));
            return result >=  0;
        }
        throw new ArithmeticException("[Ge]args not a number");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeExpression that = (GeExpression) o;
        return Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
