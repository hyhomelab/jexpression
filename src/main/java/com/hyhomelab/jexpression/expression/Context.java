package com.hyhomelab.jexpression.expression;

import com.hyhomelab.jexpression.expression.nontermial.function.Func;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/11/21 17:41
 */
public interface Context {
    Func getFunc(String funcName);

    Object getVar(String name);

    void setVar(String name, Object value);
}
