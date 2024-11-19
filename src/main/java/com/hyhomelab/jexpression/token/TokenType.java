package com.hyhomelab.jexpression.token;

public enum TokenType {
    START(0),// 起始位置
    NUMBER(1),  // 数字
    OP(2),  // 运算符
    LEFT_BRACKET(3),  // 左括号
    RIGHT_BRACKET(4),  // 右括号
    DECIMAL(5),  // 小数
    FUNC(6),  // 方法
    KEY_WORD(7), // 关键字
    COMMA(7),  // 逗号
    STRING(8),  // 字符串
    VAR(9),  // 变量
    BLANK_SPACE(10), // 空格
    END(100); // 结束符号


    private final int value;


    TokenType(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }
}
