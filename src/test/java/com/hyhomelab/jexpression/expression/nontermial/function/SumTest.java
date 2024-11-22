package com.hyhomelab.jexpression.expression.nontermial.function;

import com.hyhomelab.jexpression.functions.math.Sum;
import com.hyhomelab.jexpression.runtime.RuntimeContext;
import junit.framework.TestCase;

import java.math.BigDecimal;

public class SumTest extends TestCase {
    public void testSum() {
        Sum sum = new Sum();
        var result = sum.call(new RuntimeContext(), new Object[]{1,2,3});
        assertEquals(new BigDecimal(6), result);
    }
}