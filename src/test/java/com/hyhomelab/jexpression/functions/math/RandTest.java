package com.hyhomelab.jexpression.functions.math;

import junit.framework.TestCase;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/18 17:19
 */
public class RandTest extends TestCase {

    public void testCall() {
        var f = new Rand();
        var result = f.call(null);
        System.out.println(result);
        assertNotNull(result);
    }
}