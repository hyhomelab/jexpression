package com.hyhomelab.jexpression.scanner;

public class Start extends AbsTransfer {

    @Override
    public Transferable to(Context ctx, char data, State from) {
        if(Helper.isBlankSpace(data)){
            return new Transferable(data, State.BLANK_SPACE);
        } else if(Helper.isLeftBracket(data)){
            return new Transferable(data, State.LEFT_BRACKET);
        }else if(Helper.isZero(data)){
            return new Transferable(data, State.ZERO);
        }else if(Helper.isOpMinus(data) || Helper.isNumber(data)) {
            return new Transferable(data, State.NUMBER);
        }else if(Helper.isAllowString(data)){
            return new Transferable(data, State.UNIDENTIFIED_STR);
        }else if(Helper.isSingleQuotes(data)){
            return new Transferable(data, State.STR_START);
        }
        return null;
    }

    @Override
    public State current() {
        return State.START;
    }
}
