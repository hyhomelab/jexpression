package com.hyhomelab.jexpression.functions.math;

import junit.framework.TestCase;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 11:03
 */
public class RoundTest extends TestCase {

    public void testCall() {
        Round f = new Round();
        var result = f.call(null, -1.1);
        assertEquals(-1, result);

        result = f.call(null, 1.1);
        assertEquals(1, result);

        result = f.call(null, 1.5);
        assertEquals(2, result);
    }
}