package com.hyhomelab.jexpression.runtime;

import com.hyhomelab.jexpression.expression.nontermial.function.Func;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/11/21 17:52
 */
public interface FuncFinder {
    Func getFunc(String funcName);
}
