package com.hyhomelab.jexpression.functions.str;

import com.hyhomelab.jexpression.expression.terminal.VarExpression;
import com.hyhomelab.jexpression.runtime.RuntimeContext;
import junit.framework.TestCase;

import java.math.BigDecimal;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 11:03
 */
public class FindLastTest extends TestCase {

    public void testCall() {
        var f =new FindLast();
        var result = f.call(null,  "集美区","集美区集美区");
        assertEquals(new BigDecimal(3), result);

        // 包含变量
        var ctx = new RuntimeContext();
        ctx.setVar("订单地址","集美区集美区");
        result = f.call(ctx, "集美区",new VarExpression("订单地址"));
        assertEquals(new BigDecimal(3), result);
    }
}