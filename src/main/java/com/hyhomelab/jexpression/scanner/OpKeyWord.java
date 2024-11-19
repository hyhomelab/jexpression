package com.hyhomelab.jexpression.scanner;

public class OpKeyWord extends AbsTransfer{
    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if (Helper.isBlankSpace(data)){
            return new Transferable(data, State.BLANK_SPACE);
        }else if(Helper.isAllowString(data)){
            return new Transferable(data, State.UNIDENTIFIED_STR);
        }else if(Helper.isLeftBracket(data)){
            return new Transferable(data, State.LEFT_BRACKET);
        }else if(Helper.isNumber(data) || Helper.isOpMinus(data)){
            return new Transferable(data, State.NUMBER);
        }else if(Helper.isZero(data)){
            return new Transferable(data, State.ZERO);
        }
        return null;
    }

    @Override
    public State current() {
        return State.OP_KEY_WORD;
    }
}
