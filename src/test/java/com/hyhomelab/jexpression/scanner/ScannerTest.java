package com.hyhomelab.jexpression.scanner;

import com.hyhomelab.jexpression.token.*;
import junit.framework.TestCase;

import java.util.List;

public class ScannerTest extends TestCase {

    public void testScanPlus() {
        String exp = "1+2";
        Scanner scanner = new Scanner();
        List<Token> tokens = scanner.scan(exp);
        List<Token> expectedTokens = List.of(
                new Token(TokenType.START, "", 0, 0),
                new Token(TokenType.NUMBER,"1", 0, 1),
                new Token(TokenType.OP,"+", 1,2),
                new Token(TokenType.NUMBER, "2", 2, 3),
                new Token(TokenType.END,"", 3, 3)
        );
        assertEquals(exp, expectedTokens,tokens);

    }
    public void testScanVar() {
        String exp = "var1 + var2";
        Scanner scanner = new Scanner();
        List<Token> tokens = scanner.scan(exp);
        List<Token> expectedTokens = List.of(
                new Token(TokenType.START, "",0, 0),
                new Token(TokenType.VAR, "var1", 0, 4),
                new Token(TokenType.OP, "+", 5,6),
                new Token(TokenType.VAR, "var2", 7, 11),
                new Token(TokenType.END,"", 11, 11)
        );
        assertEquals(exp, expectedTokens,tokens);
    }

    public void testScanDecimal() {
        String exp = "11 * ( 12 + 13.1 )";
        Scanner scanner = new Scanner();
        List<Token> tokens = scanner.scan(exp);
        List<Token> expectedTokens = List.of(
                new Token(TokenType.START, "",0, 0),
                new Token(TokenType.NUMBER, "11", 0, 2),
                new Token(TokenType.OP, "*", 3,4),
                new Token(TokenType.LEFT_BRACKET, "(", 5, 6),
                new Token(TokenType.NUMBER,"12", 7, 9),
                new Token(TokenType.OP,"+", 10, 11),
                new Token(TokenType.DECIMAL,"13.1", 12, 16),
                new Token(TokenType.RIGHT_BRACKET,")", 17, 18),
                new Token(TokenType.END,"", 18, 18)
        );
        assertEquals(exp, expectedTokens,tokens);
    }

    public void testScanFunc() {
        String exp = "sum(1,2,3)+4";
        Scanner scanner = new Scanner();
        List<Token> tokens = scanner.scan(exp);
        List<Token> expectedTokens = List.of(
                new Token(TokenType.START, "",0, 0),
                new Token(TokenType.FUNC, "sum", 0, 3),
                new Token(TokenType.LEFT_BRACKET, "(", 3,4),
                new Token(TokenType.NUMBER, "1", 4, 5),
                new Token(TokenType.NUMBER,"2", 6, 7),
                new Token(TokenType.NUMBER,"3", 8, 9),
                new Token(TokenType.RIGHT_BRACKET,")", 9, 10),
                new Token(TokenType.OP,"+", 10, 11),
                new Token(TokenType.NUMBER,"4", 11, 12),
                new Token(TokenType.END,"", 12, 12)
        );
        assertEquals(exp, expectedTokens,tokens);
    }
    public void testScanString() {
        String exp = "'abc' + 'def'";
        Scanner scanner = new Scanner();
        List<Token> tokens = scanner.scan(exp);
        List<Token> expectedTokens = List.of(
                new Token(TokenType.START, "",0, 0),
                new Token(TokenType.STRING, "abc", 1, 4),
                new Token(TokenType.OP, "+", 6,7),
                new Token(TokenType.STRING, "def", 9, 12),
                new Token(TokenType.END,"", 13, 13)
        );
        assertEquals(exp, expectedTokens,tokens);
    }

    public void testScanZero() {
        String exp = "0 + 0";
        Scanner scanner = new Scanner();
        List<Token> tokens = scanner.scan(exp);
        List<Token> expectedTokens = List.of(
                new Token(TokenType.START, "",0, 0),
                new Token(TokenType.NUMBER, "0", 0, 1),
                new Token(TokenType.OP, "+", 2,3),
                new Token(TokenType.NUMBER, "0", 4, 5),
                new Token(TokenType.END,"", 5, 5)
        );
        assertEquals(exp, expectedTokens,tokens);
    }
    public void testScanMinus() {
        String exp = "-2--3";
        Scanner scanner = new Scanner();
        List<Token> tokens = scanner.scan(exp);
        List<Token> expectedTokens = List.of(
                new Token(TokenType.START, "",0, 0),
                new Token(TokenType.NUMBER, "-2", 0, 2),
                new Token(TokenType.OP, "-", 2,3),
                new Token(TokenType.NUMBER, "-3", 3, 5),
                new Token(TokenType.END,"", 5, 5)
        );
        assertEquals(exp, expectedTokens,tokens);
    }

    public void testScanBracket() {
        String exp = "( (1+2) )";
        Scanner scanner = new Scanner();
        List<Token> tokens = scanner.scan(exp);
        List<Token> expectedTokens = List.of(
                new Token(TokenType.START, "",0, 0),
                new Token(TokenType.LEFT_BRACKET, "(", 0, 1),
                new Token(TokenType.LEFT_BRACKET, "(", 2,3),
                new Token(TokenType.NUMBER, "1", 3, 4),
                new Token(TokenType.OP, "+", 4, 5),
                new Token(TokenType.NUMBER, "2", 5, 6),
                new Token(TokenType.RIGHT_BRACKET, ")", 6, 7),
                new Token(TokenType.RIGHT_BRACKET, ")", 8, 9),
                new Token(TokenType.END,"", 9, 9)
        );
        assertEquals(exp, expectedTokens,tokens);
    }
}