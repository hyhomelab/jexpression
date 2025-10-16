package com.hyhomelab.jexpression.functions.base;

import com.hyhomelab.jexpression.expression.terminal.NumberExpression;
import com.hyhomelab.jexpression.functions.math.Max;
import com.hyhomelab.jexpression.runtime.RuntimeContext;
import junit.framework.TestCase;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/18 17:08
 */
public class BetweenTest extends TestCase {

    public void testCall() {
        var f = new Between();
        var result = f.call(new RuntimeContext(), new NumberExpression("2"),new NumberExpression("1"),new NumberExpression("3"));
        assertEquals(true, result);

        var max = new Max();
        var maxResult = max.call(new RuntimeContext(), new NumberExpression("1"),new NumberExpression("2"),new NumberExpression("4"));
        result = f.call(new RuntimeContext(), maxResult,new NumberExpression("1"),new NumberExpression("3"));
        assertEquals(false, result);
    }
}