package com.hyhomelab.jexpression.functions.math;

import junit.framework.TestCase;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/18 17:45
 */
public class RandBetweenTest extends TestCase {

    public void testCalc() {
        RandBetween randBetween = new RandBetween();
        var result = randBetween.calc(0.001f, 1, 3);
        assertEquals(1, result);

        result = randBetween.calc(0.99f, 1, 3);
        assertEquals(3, result);
    }
}