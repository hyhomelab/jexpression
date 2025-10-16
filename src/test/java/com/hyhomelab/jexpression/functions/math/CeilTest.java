package com.hyhomelab.jexpression.functions.math;

import junit.framework.TestCase;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 11:03
 */
public class CeilTest extends TestCase {

    public void testCall() {
        Ceil ceil = new Ceil();
        var result = ceil.call(null, -1.1);
        assertEquals(-1L, result);

        result = ceil.call(null, -0.9);
        assertEquals(0L, result);

        result = ceil.call(null, 1.1);
        assertEquals(2L, result);

        result = ceil.call(null, 3.14, 1);
        assertEquals(3.2, result);


        result = ceil.call(null, 3.15, 1);
        assertEquals(3.2, result);
    }
}