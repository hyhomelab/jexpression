package com.hyhomelab.jexpression.scanner;

/**
 * 状态枚举
 */
public enum State{
    NUMBER,  // 数字
    OP,  // 运算符
    BLANK_SPACE, // 空格
    LEFT_BRACKET,  // 左括号
    RIGHT_BRACKET,  // 右括号
    ZERO, // 数字0
    DECIMAL,  // 小数
    POINT_OP,  // 小数点，后续可扩展成属性访问符
    FUNC,  // 方法
    COMMA,  // 逗号
    UNIDENTIFIED_STR, // 未识别字符串
    UNIDENTIFIED_OP_MINUS, // 未识别字符串
    STR_START, // 字符串开始
    STR,  // 字符串
    STR_END,  // 字符串结束
    VAR,  // 变量
    OP_KEY_WORD,  // 运算符关键字
    START, // 开始符号
    END; // 结束符号

}
