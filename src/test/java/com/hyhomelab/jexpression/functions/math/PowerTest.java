package com.hyhomelab.jexpression.functions.math;

import junit.framework.TestCase;

import java.math.BigDecimal;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 11:36
 */
public class PowerTest extends TestCase {

    public void testCall() {
        Power power = new Power();
        var result = power.call(null, 2, 3);
        assertEquals(new BigDecimal(8), result);

        result = power.call(null, 1.1, 2);
        assertEquals(new BigDecimal("1.21"), result);
    }
}