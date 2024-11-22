package com.hyhomelab.jexpression.runtime;

import com.hyhomelab.jexpression.ast.Ast;
import com.hyhomelab.jexpression.exception.FunctionNotFoundException;
import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Expression;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;
import com.hyhomelab.jexpression.expression.nontermial.function.Library;
import com.hyhomelab.jexpression.functions.FuncLibraryLoader;
import com.hyhomelab.jexpression.scanner.Scanner;
import com.hyhomelab.jexpression.utils.WeakCache;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runtime {

    private final List<Library> funcLibrary = new ArrayList<>();
    private final WeakCache<Expression> cache = new WeakCache<>(10);

    public final static String CUSTOM_LIBRARY_NAME = "custom";


    public Runtime() {
        this.loadDefaultLibraries();
    }

    private void loadDefaultLibraries() {
        this.loadLibrary(new Library(CUSTOM_LIBRARY_NAME));
        this.loadLibrary(FuncLibraryLoader.loadDefaultLibraries());
    }

    private void loadLibrary(Library... libraries) {
        if (libraries != null) {
            this.funcLibrary.addAll(Arrays.asList(libraries));
        }
    }

    private Library getCustomLibrary() {
        for (Library library : funcLibrary) {
            if (library.getName().equals(CUSTOM_LIBRARY_NAME)) {
                return library;
            }
        }
        throw new RuntimeException("Custom library not found");
    }

    public void addFunc(Func func) {
        var customLib = this.getCustomLibrary();
        customLib.loadFunction(func);
    }

    public Object executeObject(String expression) {
        return this.execute0(new RuntimeContext(), expression);
    }
    public Object executeObject(Context ctx, String expression) {
        return this.execute0(ctx, expression);
    }

    public Result execute(String expression) {
        var result = this.execute0(new RuntimeContext(), expression);
        return new Result(result);
    }

    public Result execute(Context ctx, String expression) {
        var result = this.execute0(ctx, expression);
        return new Result(result);
    }

    private Object execute0(Context ctx, String expression) {
        // 加载方法
        var rtCtx = new RuntimeContext(ctx);
        rtCtx.setFuncFinder(funcName -> {
            for (Library library : funcLibrary) {
                var func = library.getFunction(funcName);
                if (func != null) {
                    return func;
                }
            }
            throw new FunctionNotFoundException(String.format("Function [%s] not found", funcName));
        });

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
        return rootExp.interpret(rtCtx);
    }


}
