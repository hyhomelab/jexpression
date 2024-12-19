package com.hyhomelab.jexpression.functions.base;

import com.hyhomelab.jexpression.functions.math.Min;
import com.hyhomelab.jexpression.runtime.RuntimeContext;
import junit.framework.TestCase;

import java.math.BigDecimal;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/18 17:08
 */
public class MinTest extends TestCase {

    public void testCall() {
        var f = new Min();
        var result = f.call(new RuntimeContext(), 1,2,3);
        assertEquals(new BigDecimal("1"), result);
    }
}