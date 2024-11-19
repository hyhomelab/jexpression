package com.hyhomelab.jexpression.expression.terminal;

import com.hyhomelab.jexpression.expression.Context;
import com.hyhomelab.jexpression.expression.Terminal;

import java.math.BigDecimal;
import java.util.Objects;

public class NumberExpression extends Terminal {

    private final BigDecimal value;

    public NumberExpression(String value) {
        this.value = new BigDecimal(value);
    }

    @Override
    public Object interpret(Context ctx) {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NumberExpression that = (NumberExpression) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(value);
    }
}
