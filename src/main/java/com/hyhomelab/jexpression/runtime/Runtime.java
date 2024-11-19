package com.hyhomelab.jexpression.runtime;

import com.hyhomelab.jexpression.ast.Ast;
import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Expression;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.expression.nontermial.function.Sum;
import com.hyhomelab.jexpression.scanner.Scanner;
import com.hyhomelab.jexpression.utils.WeakCache;

import java.util.HashMap;
import java.util.Map;

public class Runtime {

    private final Map<String, Func> funcMap = new HashMap<String, Func>();
    private final WeakCache<Expression> cache = new WeakCache<>(10);


    public Runtime() {
        this.loadBaseFunc();
    }

    private void loadBaseFunc() {
        this.addFunc(new Sum());
    }

    public void addFunc(Func func) {
        this.funcMap.put(func.getName(), func);
    }

    public Object executeObject(String expression) {
        return this.execute0(new Context(), expression);
    }
    public Object executeObject(Context ctx, String expression) {
        return this.execute0(ctx, expression);
    }

    public Result execute(String expression) {
        var result = this.execute0(new Context(), expression);
        return new Result(result);
    }

    public Result execute(Context ctx, String expression) {
        var result = this.execute0(ctx, expression);
        return new Result(result);
    }

    private Object execute0(Context ctx, String expression) {
        // 加载方法库
        ctx.setFuncMap(this.funcMap);

        var rootExp = this.cache.get(expression);
        if (rootExp == null) {
            // scan
            var scanner = new Scanner();
            var tokens = scanner.scan(expression);
            // build ast
            var ast = new Ast();
            rootExp = ast.parse(tokens);
            // 缓存
            this.cache.put(expression, rootExp);
        }
        // exec
        return rootExp.interpret(ctx);
    }


}
