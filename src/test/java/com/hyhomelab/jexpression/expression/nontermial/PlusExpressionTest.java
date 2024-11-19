package com.hyhomelab.jexpression.expression.nontermial;

import com.hyhomelab.jexpression.expression.terminal.NumberExpression;
import com.hyhomelab.jexpression.expression.terminal.StringExpression;
import junit.framework.TestCase;

import java.math.BigDecimal;

public class PlusExpressionTest extends TestCase {

    public void testInterpretString() {
        var plus = new PlusExpression(new StringExpression("111"), new StringExpression("222"));
        var result = plus.interpret(null);
        assertEquals("111222", result);

    }

    public void testInterpretNumber() {
        var plus = new PlusExpression(new NumberExpression("111"), new NumberExpression("222"));
        var result = plus.interpret(null);
        assertEquals(new BigDecimal("333"), result);

    }
}