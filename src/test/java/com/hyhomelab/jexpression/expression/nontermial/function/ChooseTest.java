package com.hyhomelab.jexpression.expression.nontermial.function;

import com.hyhomelab.jexpression.expression.nontermial.EqExpression;
import com.hyhomelab.jexpression.expression.nontermial.FuncExpression;
import com.hyhomelab.jexpression.expression.terminal.NumberExpression;
import com.hyhomelab.jexpression.expression.terminal.StringExpression;
import com.hyhomelab.jexpression.expression.terminal.VarExpression;
import com.hyhomelab.jexpression.functions.base.Choose;
import com.hyhomelab.jexpression.runtime.RuntimeContext;
import junit.framework.TestCase;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/11/21 11:36
 */
public class ChooseTest extends TestCase {

    public void testCall() {
        var func = new Choose();
        var ctx = new RuntimeContext();
        ctx.setVar("gpa", 3);
        ctx.setFuncFinder(funcName -> func);
        // choose(gpa==4,'A', gpa==3, 'B', gpa==2,'C', gpa==1, 'D', 'F')
        var funcExp = new FuncExpression("choose",
                new EqExpression(
                        new VarExpression("gpa"),
                        new NumberExpression("4")
                ),
                new StringExpression("A"),
                new EqExpression(
                        new VarExpression("gpa"),
                        new NumberExpression("3")
                ),
                new StringExpression("B"),
                new EqExpression(
                        new VarExpression("gpa"),
                        new NumberExpression("2")
                ),
                new StringExpression("C"),
                new EqExpression(
                        new VarExpression("gpa"),
                        new NumberExpression("1")
                ),
                new StringExpression("D"),
                new StringExpression("F")
        );
        ctx.setVar("gpa", 4);
        assertEquals("A", funcExp.interpret(ctx));

        ctx.setVar("gpa", 3);
        assertEquals("B", funcExp.interpret(ctx));

        ctx.setVar("gpa", 2);
        assertEquals("C", funcExp.interpret(ctx));

        ctx.setVar("gpa", 1);
        assertEquals("D", funcExp.interpret(ctx));

        ctx.setVar("gpa", 0);
        assertEquals("F", funcExp.interpret(ctx));
    }
}