package com.hyhomelab.jexpression.scanner;

public class StrEnd extends AbsTransfer{
    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if (Helper.isBlankSpace(data)){
            return new Transferable(data, State.BLANK_SPACE);
        }else if(Helper.isComma(data)){
            return new Transferable(data, State.COMMA);
        } else if (Helper.isRightBracket(data)) {
            return new Transferable(data, State.RIGHT_BRACKET);
        }else if(data == '='){
            return new Transferable(data, State.OP);
        }else if(Helper.isAllowString(data)){
            return new Transferable(data, State.UNIDENTIFIED_STR);
        }
        return null;
    }


    @Override
    public State current() {
        return State.STR_END;
    }
}
