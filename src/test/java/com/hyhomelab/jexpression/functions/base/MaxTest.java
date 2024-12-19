package com.hyhomelab.jexpression.functions.base;

import com.hyhomelab.jexpression.expression.terminal.NumberExpression;
import com.hyhomelab.jexpression.functions.math.Max;
import com.hyhomelab.jexpression.runtime.RuntimeContext;
import junit.framework.TestCase;

import java.math.BigDecimal;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/18 17:08
 */
public class MaxTest extends TestCase {

    public void testCall() {
        var f = new Max();
        var result = f.call(new RuntimeContext(), new NumberExpression("1"),new NumberExpression("2"),new NumberExpression("3"));
        assertEquals(new BigDecimal("3"), result);
    }
}