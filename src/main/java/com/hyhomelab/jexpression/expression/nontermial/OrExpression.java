package com.hyhomelab.jexpression.expression.nontermial;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Expression;
import com.hyhomelab.jexpression.expression.Nonterminal;

import java.util.Objects;

public class OrExpression extends Nonterminal {
    private final Expression left;
    private final Expression right;

    public OrExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public Object interpret(Context ctx) {
        var leftResult = left.interpret(ctx);
        var rightResult = right.interpret(ctx);
        if (leftResult instanceof Boolean && rightResult instanceof Boolean) {
            return ((Boolean) leftResult || (Boolean) rightResult);
        }
        throw new ArithmeticException("[And]" + leftResult + " and " + rightResult + " are not boolean");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrExpression that = (OrExpression) o;
        return Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
