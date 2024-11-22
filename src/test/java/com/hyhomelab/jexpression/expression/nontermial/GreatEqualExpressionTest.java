package com.hyhomelab.jexpression.expression.nontermial;

import com.hyhomelab.jexpression.expression.terminal.DecimalExpression;
import com.hyhomelab.jexpression.expression.terminal.NumberExpression;
import com.hyhomelab.jexpression.runtime.RuntimeContext;
import junit.framework.TestCase;

public class GreatEqualExpressionTest extends TestCase {
    public void testGreatEqual() {
        var expEqual = new GeExpression(new NumberExpression("1"), new DecimalExpression("1.0"));
        var resultEqual = expEqual.interpret(new RuntimeContext());
        assertEquals(true, resultEqual);
    }
    public void testFloat() {
        var expEqual = new GeExpression(new NumberExpression("1.1"), new DecimalExpression("1.0"));
        var resultEqual = expEqual.interpret(new RuntimeContext());
        assertEquals(true, resultEqual);
    }
}