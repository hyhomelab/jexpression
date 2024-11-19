package com.hyhomelab.jexpression.scanner;

import java.util.List;

public class Helper {
    public static boolean isAllowString(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_' || (c >= '\u4E00' && c <= '\u9FFF');
    }

    public static boolean isNumber(char data) {
        return (data >= '1' && data <= '9');
    }

    public static boolean isNumberWithZero(char data) {
        return (data >= '0' && data <= '9');
    }

    public static boolean isOp(char data) {
        return List.of('+','-', '*', '/', '>', '<', '|', '&', '=').contains(data);
    }

    public static boolean isComma(char data) {
        return data == ',' || data == '，';
    }

    public static boolean isRightBracket(char data) {
        return data == ')' || data == '）';
    }

    public static boolean isLeftBracket(char data) {
        return data == '(' || data == '（';
    }

    public static boolean isPointOp(char data) {
        return data == '.';
    }

    public static boolean isZero(char data) {
        return data == '0';
    }

    public static boolean isBlankSpace(char data) {
        return data == ' ' || data == '\t' || data == '\n' || data == '\r';
    }

    public static boolean isSingleQuotes(char data) {
        return data == '\'';
    }

    public static boolean isOpMinus(char data) {
        return data == '-';
    }

    /**
     * 复合运算符
     * @param data
     * @return
     */
    public static boolean isCompositeOp(char data) {
        return data == '|' || data == '&' || data == '=';
    }

    public static boolean isKeyWord(String currentWord) {
        return List.of("in").contains(currentWord);
    }
}
