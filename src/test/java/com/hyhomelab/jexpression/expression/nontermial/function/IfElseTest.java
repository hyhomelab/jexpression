package com.hyhomelab.jexpression.expression.nontermial.function;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.nontermial.FuncExpression;
import com.hyhomelab.jexpression.expression.nontermial.GeExpression;
import com.hyhomelab.jexpression.expression.terminal.NumberExpression;
import com.hyhomelab.jexpression.expression.terminal.StringExpression;
import com.hyhomelab.jexpression.expression.terminal.VarExpression;
import junit.framework.TestCase;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/11/21 11:15
 */
public class IfElseTest extends TestCase {

    public void testCall() {
        var func = new IfElse();
        var ctx = new Context();
        ctx.addFunc(func);
        var funcExp = new FuncExpression("ifElse",
                new GeExpression(
                        new VarExpression("age"),
                        new NumberExpression("18")
                ),
                new StringExpression("adult"),
                new StringExpression("child")
        );
        ctx.setVar("age", 18);
        assertEquals("adult", funcExp.interpret(ctx));

        ctx.setVar("age", 17);
        assertEquals("child", funcExp.interpret(ctx));
    }
}