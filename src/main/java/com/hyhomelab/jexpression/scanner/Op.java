package com.hyhomelab.jexpression.scanner;

public class Op extends AbsTransfer{
    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if (Helper.isBlankSpace(data)){
            return new Transferable(data, State.BLANK_SPACE);
        }else if (Helper.isLeftBracket(data)){
            return new Transferable(data, State.LEFT_BRACKET);
        }else if(Helper.isAllowString(data)){
            return new Transferable(data, State.VAR);
        }else if(Helper.isCompositeOp(data)){
            return new Transferable(data, State.OP);
        }else if(Helper.isOpMinus(data) || Helper.isNumber(data)){
            return new Transferable(data, State.NUMBER);
        }else if(Helper.isZero(data)){
            return new Transferable(data, State.ZERO);
        }else if(Helper.isSingleQuotes(data)){
            return new Transferable(data, State.STR_START);
        }
        return null;
    }


    @Override
    public State current() {
        return State.OP;
    }
}
