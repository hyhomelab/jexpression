package com.hyhomelab.jexpression.expression.terminal;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Terminal;

import java.util.Objects;

public class StringExpression extends Terminal {
    private final String value;
    public StringExpression(String value) {
        this.value = value;
    }

    @Override
    public Object interpret(Context ctx) {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringExpression that = (StringExpression) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
