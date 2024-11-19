package com.hyhomelab.jexpression.expression.nontermial;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Expression;
import com.hyhomelab.jexpression.expression.ExpressionType;
import com.hyhomelab.jexpression.expression.terminal.NumberExpression;
import com.hyhomelab.jexpression.expression.terminal.StringExpression;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.List;

public class InExpressionTest extends TestCase {
    public void testInBigDecimal() {
        var in = new InExpression(new NumberExpression("1"), new Expression() {
            @Override
            public Object interpret(Context ctx) {
                return List.of(new BigDecimal(1), new BigDecimal(2), new BigDecimal(3));
            }

            @Override
            public ExpressionType getType() {
                return null;
            }
        });
        var result = in.interpret(new Context());
        assertEquals(true, result);
    }

    public void testInString() {
        var in = new InExpression(new StringExpression("1"), new Expression() {
            @Override
            public Object interpret(Context ctx) {
                return List.of("1", "2", "3");
            }

            @Override
            public ExpressionType getType() {
                return null;
            }
        });
        var result = in.interpret(new Context());
        assertEquals(true, result);
    }
}