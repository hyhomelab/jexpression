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
public class FindTest extends TestCase {

    public void testCall() {
        Find f =new Find();
        var result = f.call(null,  "集美区","福建省厦门市集美区朝旭路莲花新城悦园6-2702");
        assertEquals(new BigDecimal(6), result);

         result = f.call(null,  "c","abc");
        assertEquals(new BigDecimal(2), result);

        // 包含变量
        var ctx = new RuntimeContext();
        ctx.setVar("订单地址","福建省厦门市集美区朝旭路莲花新城悦园6-2702");
        result = f.call(ctx, "集美区",new VarExpression("订单地址"));
        assertEquals(new BigDecimal(6), result);
    }
}