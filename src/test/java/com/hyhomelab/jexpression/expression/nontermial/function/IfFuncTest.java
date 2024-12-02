package com.hyhomelab.jexpression.expression.nontermial.function;

import com.hyhomelab.jexpression.expression.nontermial.FuncExpression;
import com.hyhomelab.jexpression.expression.nontermial.GeExpression;
import com.hyhomelab.jexpression.expression.terminal.NumberExpression;
import com.hyhomelab.jexpression.expression.terminal.StringExpression;
import com.hyhomelab.jexpression.expression.terminal.VarExpression;
import com.hyhomelab.jexpression.functions.base.IfFunc;
import com.hyhomelab.jexpression.runtime.RuntimeContext;
import junit.framework.TestCase;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/11/21 11:15
 */
public class IfFuncTest extends TestCase {

    public void testCall() {
        var func = new IfFunc();
        var ctx = new RuntimeContext();
        ctx.setFuncFinder(funcName -> func);
        var funcExp = new FuncExpression("if",
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