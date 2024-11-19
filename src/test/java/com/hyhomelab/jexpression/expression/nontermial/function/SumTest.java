package com.hyhomelab.jexpression.expression.nontermial.function;

import com.hyhomelab.jexpression.expression.Context;
import junit.framework.TestCase;

import java.math.BigDecimal;

public class SumTest extends TestCase {
    public void testSum() {
        Sum sum = new Sum();
        var result = sum.call(new Context(), new Object[]{1,2,3});
        assertEquals(new BigDecimal(6), result);
    }
}