package com.hyhomelab.jexpression.scanner;

public class Comma extends AbsTransfer{
    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if (Helper.isBlankSpace(data)){
            return new Transferable(data, State.BLANK_SPACE);
        }else if(Helper.isZero(data)){
            return new Transferable(data, State.ZERO);
        }else if(Helper.isNumber(data)){
            return new Transferable(data, State.NUMBER);
        }else if(Helper.isSingleQuotes(data)){
            return new Transferable(data, State.STR_START);
        }else if(Helper.isAllowString(data)){
            return new Transferable(data, State.UNIDENTIFIED_STR);
        }else if(Helper.isOpMinus(data)){
            return new Transferable(data, State.NUMBER);
        }
        return null;
    }


    @Override
    public State current() {
        return State.COMMA;
    }
}
