package com.hyhomelab.jexpression.scanner;

public class Zero extends AbsTransfer{
    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if (Helper.isBlankSpace(data)){
            return new Transferable(data, State.BLANK_SPACE);
        }else if(Helper.isOp(data)){
            return new Transferable(data, State.OP);
        }else if(Helper.isRightBracket(data)){
            return new Transferable(data, State.RIGHT_BRACKET);
        }else if(Helper.isComma(data)){
            return new Transferable(data, State.COMMA);
        }else if(Helper.isPointOp(data)){
            return new Transferable(data, new TransferNext(State.DECIMAL, State.DECIMAL));
        }
        return null;
    }

    @Override
    public State current() {
        return State.ZERO;
    }
}
