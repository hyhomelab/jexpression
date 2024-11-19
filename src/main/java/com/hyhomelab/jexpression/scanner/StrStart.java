package com.hyhomelab.jexpression.scanner;

public class StrStart extends AbsTransfer{
    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if (!Helper.isSingleQuotes(data)){
            return new Transferable(data, State.STR);
        }
        return null;
    }


    @Override
    public State current() {
        return State.STR_START;
    }
}
