package com.hyhomelab.jexpression.expression.nontermial.function;

import junit.framework.TestCase;

public class StringRepeatTest extends TestCase {
    public void testStringRepeat() {
        StringRepeat function = new StringRepeat();
        var result = function.call(null, "abc", 3);
        assertEquals("abcabcabc", result);
    }
}