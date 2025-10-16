package com.hyhomelab.jexpression.functions.math;

import junit.framework.TestCase;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 11:03
 */
public class FloorTest extends TestCase {

    public void testCall() {
        Floor f = new Floor();
        var result = f.call(null, -1.1);
        assertEquals(-2L, result);

        result = f.call(null, -0.9);
        assertEquals(-1L, result);

        result = f.call(null, 1.1);
        assertEquals(1L, result);

        result = f.call(null, 3.14, 1);
        assertEquals(3.1, result);


        result = f.call(null, 3.15, 1);
        assertEquals(3.1, result);
    }
}