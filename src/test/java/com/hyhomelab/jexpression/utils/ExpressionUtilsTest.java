package com.hyhomelab.jexpression.utils;

import junit.framework.TestCase;

import java.util.List;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 16:06
 */
public class ExpressionUtilsTest extends TestCase {

    public void testFindVars() {
        String expStr = "var1+var2*func(var3)";
        List<String> vars = ExpressionUtils.findVars(expStr);
        List<String> expected = List.of("var1", "var2", "var3");
        assertEquals(expected, vars);
    }
}