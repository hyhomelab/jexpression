package com.hyhomelab.jexpression.ast;

import com.hyhomelab.jexpression.expression.Expression;
import com.hyhomelab.jexpression.expression.nontermial.FuncExpression;
import com.hyhomelab.jexpression.expression.nontermial.MultiExpression;
import com.hyhomelab.jexpression.expression.nontermial.PlusExpression;
import com.hyhomelab.jexpression.expression.nontermial.SubExpression;
import com.hyhomelab.jexpression.expression.terminal.NumberExpression;
import com.hyhomelab.jexpression.functions.math.Sum;
import com.hyhomelab.jexpression.runtime.RuntimeContext;
import com.hyhomelab.jexpression.token.Token;
import com.hyhomelab.jexpression.token.TokenType;
import junit.framework.TestCase;

import java.math.BigDecimal;
import java.util.List;

public class AstTest extends TestCase {

    public void testParsePlus() {
        // 1+2
        var tokens = List.of(
                new Token(TokenType.NUMBER, "1", 0, 0),
                new Token(TokenType.OP, "+", 0, 0),
                new Token(TokenType.NUMBER, "2", 0, 0)
                );
        Expression root = new Ast().parse(tokens);
        Expression expectRoot = new PlusExpression(new NumberExpression("1"), new NumberExpression("2"));
        assertEquals(expectRoot.getClass(), root.getClass());
    }

    public void testBuildPlusNode() {
        // 1+2
        var tokens = List.of(
                new Token(TokenType.NUMBER, "1", 0, 0),
                new Token(TokenType.OP, "+", 0, 0),
                new Token(TokenType.NUMBER, "2", 0, 0)
        );
        Node root = new Ast().buildTree(tokens);
        Node expectRoot = new Node(new Token(TokenType.OP, "+", 0, 0));
        expectRoot.addNodes(
                new Node(new Token(TokenType.NUMBER, "1", 0, 0)),
                new Node(new Token(TokenType.NUMBER, "2", 0, 0))
        );
        assertEquals(expectRoot, root);
    }
    public void testParseSingleNumber() {
        // 1
        var tokens = List.of(
                new Token(TokenType.NUMBER, "1", 0, 0)
        );
        Expression root = new Ast().parse(tokens);
        Expression expectRoot = new NumberExpression("1");
        assertEquals(expectRoot.getClass(), root.getClass());

        var result = root.interpret(null);
        assertEquals(new BigDecimal(1), result);
    }

    public void testBuildSingleNumberNode() {
        // 1
        var tokens = List.of(
                new Token(TokenType.NUMBER, "1", 0, 0)
        );
        Node root = new Ast().buildTree(tokens);

        assertEquals(root, new Node(new Token(TokenType.NUMBER, "1", 0, 0)));
    }

    public void testParseFunc() {
        // sum(1,2,3)
        var tokens = List.of(
                new Token(TokenType.FUNC, "sum", 0, 0),
                new Token(TokenType.LEFT_BRACKET, "(", 0, 0),
                new Token(TokenType.NUMBER, "1", 0, 0),
                new Token(TokenType.NUMBER, "2", 0, 0),
                new Token(TokenType.NUMBER, "3", 0, 0),
                new Token(TokenType.RIGHT_BRACKET, ")", 0, 0)
                );
        Expression root = new Ast().parse(tokens);
        Expression expectRoot = new FuncExpression("sum", new NumberExpression("1"), new NumberExpression("2"), new NumberExpression("3"));
        assertEquals(expectRoot, root);
        // calc
        var ctx = new RuntimeContext();
        ctx.setFuncFinder(funcName -> new Sum());
        var result = root.interpret(ctx);
        assertEquals(new BigDecimal(6), result);
    }

    public void testBuildFuncNode() {
        // sum(1,2,3)
        var tokens = List.of(
                new Token(TokenType.FUNC, "sum", 0, 0),
                new Token(TokenType.LEFT_BRACKET, "(", 0, 0),
                new Token(TokenType.NUMBER, "1", 0, 0),
                new Token(TokenType.NUMBER, "2", 0, 0),
                new Token(TokenType.NUMBER, "3", 0, 0),
                new Token(TokenType.RIGHT_BRACKET, ")", 0, 0)
        );
        Node rootNode = new Ast().buildTree(tokens);
        Node expectRoot = new Node(new Token(TokenType.FUNC, "sum", 0, 0));
        expectRoot.addNodes(
                new Node(new Token(TokenType.NUMBER, "1", 0, 0)),
                new Node(new Token(TokenType.NUMBER, "2", 0, 0)),
                new Node(new Token(TokenType.NUMBER, "3", 0, 0))
        );

        assertEquals(expectRoot, rootNode);

    }

    // 1+(2-3)*4
    public void testParseBracket() {
        var tokens = List.of(
                new Token(TokenType.NUMBER, "1", 0, 0),
                new Token(TokenType.OP, "+", 0, 0),
                new Token(TokenType.LEFT_BRACKET, "(", 0, 0),
                new Token(TokenType.NUMBER, "2", 0, 0),
                new Token(TokenType.OP, "-", 0, 0),
                new Token(TokenType.NUMBER, "3", 0, 0),
                new Token(TokenType.RIGHT_BRACKET, ")", 0, 0),
                new Token(TokenType.OP, "*", 0, 0),
                new Token(TokenType.NUMBER, "4", 0, 0)
                );
        Expression root = new Ast().parse(tokens);
        Expression expectRoot = new PlusExpression(
                new NumberExpression("1"),
                new MultiExpression(
                    new SubExpression(
                            new NumberExpression("2"),
                            new NumberExpression("3")
                    ),new NumberExpression("4")));
        assertEquals(expectRoot, root);
        // 计算结果
        var result = root.interpret(new RuntimeContext());
        assertEquals(new BigDecimal( -3), result);
    }

    // 1+(2-3)*4
    public void testBuildBracketNode() {
        var tokens = List.of(
                new Token(TokenType.NUMBER, "1", 0, 0),
                new Token(TokenType.OP, "+", 0, 0),
                new Token(TokenType.LEFT_BRACKET, "(", 0, 0),
                new Token(TokenType.NUMBER, "2", 0, 0),
                new Token(TokenType.OP, "-", 0, 0),
                new Token(TokenType.NUMBER, "3", 0, 0),
                new Token(TokenType.RIGHT_BRACKET, ")", 0, 0),
                new Token(TokenType.OP, "*", 0, 0),
                new Token(TokenType.NUMBER, "4", 0, 0)
        );
        Node root = new Ast().buildTree(tokens);
        Node expectRoot = new Node(new Token(TokenType.OP, "+", 0, 0));
        expectRoot.addNodes(
                new Node(new Token(TokenType.NUMBER, "1", 0, 0)),
                new Node(new Token(TokenType.OP, "*", 0, 0)).addNodes(
                        new Node( new Token(TokenType.OP, "-", 0, 0)).addNodes(
                                new Node(new Token(TokenType.NUMBER, "2", 0, 0)),
                                new Node(new Token(TokenType.NUMBER, "3", 0, 0))
                        ),
                        new Node(new Token(TokenType.NUMBER, "4", 0, 0))
                )
        );
        assertEquals(expectRoot, root);
    }

}