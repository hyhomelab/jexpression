package com.hyhomelab.jexpression.expression.nontermial;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Expression;
import com.hyhomelab.jexpression.expression.Nonterminal;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 加法
 */
public class PlusExpression extends Nonterminal {
    private final Expression left;
    private final Expression right;
    public PlusExpression(Expression left, Expression right) {
        if (left == null || right == null) {
            throw new IllegalArgumentException("args cannot be null");
        }
        this.left = left;
        this.right = right;
    }


    @Override
    public Object interpret(Context ctx) {
        var leftValue = left.interpret(ctx);
        var rightValue = right.interpret(ctx);
        // 支持数值相加 && 字符串拼接
        if (leftValue instanceof Number && rightValue instanceof Number){
            return new BigDecimal(leftValue.toString()).add(new BigDecimal(rightValue.toString()));
        } else if (leftValue instanceof String && rightValue instanceof String) {
            return leftValue.toString() + rightValue.toString();
        }
        throw new ArithmeticException("Cannot plus " + leftValue + " and " + rightValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlusExpression that = (PlusExpression) o;
        return Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
