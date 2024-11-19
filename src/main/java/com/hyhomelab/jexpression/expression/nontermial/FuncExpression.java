package com.hyhomelab.jexpression.expression.nontermial;

import com.hyhomelab.jexpression.exception.FunctionNotFoundException;
import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Expression;
import com.hyhomelab.jexpression.expression.Nonterminal;
import com.hyhomelab.jexpression.expression.nontermial.function.Func;

import java.util.Arrays;
import java.util.Objects;

public class FuncExpression extends Nonterminal {
    private final String funcName;
    private final Expression[] args;

    public FuncExpression(String funcName, Expression... args) {
        this.funcName = funcName;
        this.args = args;
    }

    @Override
    public Object interpret(Context ctx) {
        Func func = ctx.getFunc(this.funcName);
        if (func == null) {
            throw new FunctionNotFoundException(this.funcName);
        }
        Object[] argsResult = new Object[this.args.length];
        for (int i = 0; i < this.args.length; i++) {
            argsResult[i] = this.args[i].interpret(ctx);
        }
        return func.call(ctx, argsResult);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuncExpression that = (FuncExpression) o;
        return Objects.equals(funcName, that.funcName) && Objects.deepEquals(args, that.args);
    }

    @Override
    public int hashCode() {
        return Objects.hash(funcName, Arrays.hashCode(args));
    }
}
