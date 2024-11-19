package com.hyhomelab.jexpression.expression.nontermial.function;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Expression;
import junit.framework.TestCase;

public class NotTest extends TestCase {

    public void testNot() {
        Func f = new Not();
        var result = f.call(null, false);
        assertEquals(true, result);
    }
}