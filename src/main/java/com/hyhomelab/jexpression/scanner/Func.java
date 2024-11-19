package com.hyhomelab.jexpression.scanner;

public class Func extends AbsTransfer{
    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if(Helper.isLeftBracket(data)){
            return new Transferable(data, State.LEFT_BRACKET);
        }else if(Helper.isAllowString(data) || Helper.isNumberWithZero(data)){
            return new Transferable(data, State.FUNC);
        }
        return null;
    }

    @Override
    public State current() {
        return State.FUNC;
    }
}
