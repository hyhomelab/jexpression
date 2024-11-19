package com.hyhomelab.jexpression.scanner;

public class Var extends AbsTransfer{
    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if (Helper.isBlankSpace(data)){
            return new Transferable(data, State.BLANK_SPACE);
        }else if(Helper.isAllowString(data) || Helper.isNumberWithZero(data)){
            return new Transferable(data, State.VAR);
        }else if(Helper.isOp(data)){
            return new Transferable(data, State.OP);
        }else if(Helper.isRightBracket(data)){
            return new Transferable(data,State.RIGHT_BRACKET);
        }
        return null;
    }

    @Override
    public State current() {
        return State.VAR;
    }
}
