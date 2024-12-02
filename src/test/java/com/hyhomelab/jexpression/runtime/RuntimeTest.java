package com.hyhomelab.jexpression.runtime;

import com.hyhomelab.jexpression.functions.base.IfFunc;
import com.hyhomelab.jexpression.functions.math.Sum;
import com.hyhomelab.jexpression.functions.str.Repeat;
import junit.framework.TestCase;

import java.math.BigDecimal;

public class RuntimeTest extends TestCase {

    public void testExecuteObject() {
        String exp = "1+1";
        var runtime = new Runtime();
        var result = runtime.executeObject(exp);
        assertEquals(new BigDecimal(2), result);
    }

    public void testExecuteInt() {
        String exp = "1+1";
        var runtime = new Runtime();
        var result = runtime.execute(exp);
        assertEquals(2, result.toInt());
    }


    public void testExecuteFloat() {
        String exp = "1.1+2.2";
        var runtime = new Runtime();
        var result = runtime.execute(exp);
        assertEquals(3.3f, result.toFloat());
    }

    public void testExecuteDouble() {
        String exp = "1.111+2.222";
        var runtime = new Runtime();
        var result = runtime.execute(exp);
        assertEquals(3.333, result.toDouble());
    }

    public void testExecuteBigDecimal() {
        String exp = "1.111+2.222";
        var runtime = new Runtime();
        var result = runtime.execute(exp);
        assertEquals(new BigDecimal("3.333"), result.toBigDecimal());
    }

    public void testExecuteBoolean() {
        String exp = "1 > 2";
        var runtime = new Runtime();
        var result = runtime.execute(exp);
        assertFalse(result.toBoolean());
    }

    public void testExecuteLong() {
        String exp = "3147483647 - 1";
        var runtime = new Runtime();
        var result = runtime.execute(exp);
        assertEquals(3147483646L, result.toLong());
    }

    public void testExecuteString() {
        String exp = "3147483647 - 1";
        var runtime = new Runtime();
        var result = runtime.execute(exp);
        assertEquals(3147483646L, result.toLong());
    }

    public void testExecuteStringAndFunc() {
        String exp = "'abc' + repeat('1', 3)";
        var runtime = new Runtime();
        runtime.addFunc(new Repeat());

        var result = runtime.execute(exp);
        assertEquals("abc111", result.toString());
    }

    public void testExecuteFunc() {
        String exp = "if(sum(1,2) == 3, repeat('a',3), repeat('b',3))";
        var runtime = new Runtime();
        runtime.addFunc(new Sum());
        runtime.addFunc(new Repeat());
        runtime.addFunc(new IfFunc());

        var result = runtime.execute(exp);
        assertEquals("aaa", result.toString());
    }

    public void testExecuteChineseVar() {
        // 佣金=订单金额*20%+路费
        String exp = "订单金额*0.2 + 路费";
        var runtime = new Runtime();
        runtime.addFunc(new Repeat());
        var ctx = new RuntimeContext();
        ctx.setVar("订单金额", 200.1);
        ctx.setVar("路费", 123.2);
        var result = runtime.execute(ctx, exp);
        assertEquals(200.1*0.2+123.2, result.toDouble());
    }

    public void testExecuteBracketNest() {
        String exp = "((1+2)*(3-4))";
        var runtime = new Runtime();

        var result = runtime.execute(exp);
        assertEquals(-3, result.toInt());

        exp = "sum(1+1, 2+2, 3+3)";
        result = runtime.execute(exp);
        assertEquals(12, result.toInt());

        exp = "(1)";
        result = runtime.execute(exp);
        assertEquals(1, result.toInt());
    }

    public void testExecuteLogic() {
        String exp = "1 < 2 && 2 < 3";
        var runtime = new Runtime();

        var result = runtime.execute(exp);
        assertTrue(result.toBoolean());
    }


    public void testExecuteKeyWordIn() {
        String exp = "1 in list(1,2,3)";
        var runtime = new Runtime();

        var result = runtime.execute(exp);
        assertTrue(result.toBoolean());
    }
}