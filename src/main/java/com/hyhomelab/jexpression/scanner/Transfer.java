package com.hyhomelab.jexpression.scanner;

public interface Transfer {
    Transferable tryTo(Context ctx, char data, State from);
    State current();
}
