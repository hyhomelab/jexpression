package com.hyhomelab.jexpression.expression.nontermial.function;

import com.hyhomelab.jexpression.functions.str.Repeat;
import junit.framework.TestCase;

public class RepeatTest extends TestCase {
    public void testRepeat() {
        Repeat function = new Repeat();
        var result = function.call(null, "abc", 3);
        assertEquals("abcabcabc", result);
    }
}