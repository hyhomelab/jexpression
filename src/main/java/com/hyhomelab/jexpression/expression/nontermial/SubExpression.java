package com.hyhomelab.jexpression.expression.nontermial;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Expression;
import com.hyhomelab.jexpression.expression.Nonterminal;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 减法
 */
public class SubExpression extends Nonterminal {
    private final Expression left;
    private final Expression right;
    public SubExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }


    @Override
    public Object interpret(Context ctx) {
        var leftValue = left.interpret(ctx);
        var rightValue = right.interpret(ctx);
        // 判断两边是否都是数值
        if (!(leftValue instanceof Number)){
            throw new ArithmeticException("[Sub]parameter must be numbers");
        }
        if (!(rightValue instanceof Number)){
            throw new ArithmeticException("[Sub]parameter must be numbers");
        }
        return new BigDecimal(leftValue.toString()).subtract(new BigDecimal(rightValue.toString()));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubExpression that = (SubExpression) o;
        return Objects.equals(left, that.left) && Objects.equals(right, that.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
