package com.hyhomelab.jexpression.scanner;

public class RightBracket extends AbsTransfer{
    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if (Helper.isBlankSpace(data)){
            return new Transferable(data, State.BLANK_SPACE);
        }else if(Helper.isComma(data)){
            return new Transferable(data, State.COMMA);
        } else if (Helper.isAllowString(data)) {
            return new Transferable(data, State.OP_KEY_WORD);
        }else if(Helper.isOp(data)){
            return new Transferable(data, State.OP);
        }
        return null;
    }

    @Override
    public State current() {
        return State.RIGHT_BRACKET;
    }
}
