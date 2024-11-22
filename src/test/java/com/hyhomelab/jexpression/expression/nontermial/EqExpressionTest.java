package com.hyhomelab.jexpression.expression.nontermial;

import com.hyhomelab.jexpression.expression.terminal.NumberExpression;
import com.hyhomelab.jexpression.runtime.RuntimeContext;
import junit.framework.TestCase;

public class EqExpressionTest extends TestCase {
    public void testEqBigDecimal() {
        var eq = new EqExpression(new NumberExpression("1"), new NumberExpression("1.0"));
        var result = eq.interpret(new RuntimeContext());
        assertEquals(true, result);

        eq = new EqExpression(new NumberExpression("2"), new NumberExpression("1.0"));
        result = eq.interpret(new RuntimeContext());
        assertEquals(false, result);

        eq = new EqExpression(new NumberExpression("-0"), new NumberExpression("0"));
        result = eq.interpret(new RuntimeContext());
        assertEquals(true, result);
    }

}