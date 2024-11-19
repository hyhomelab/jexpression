package com.hyhomelab.jexpression.scanner;

public class BlankSpace extends AbsTransfer {

    @Override
    public Transferable to(Context ctx, char data, State from) {
        if(Helper.isBlankSpace(data)){
            return new Transferable(data, State.BLANK_SPACE);
        } else if(Helper.isLeftBracket(data)){
            return new Transferable(data, State.LEFT_BRACKET);
        }else if(Helper.isZero(data)){
            return new Transferable(data, State.ZERO);
        }else if(Helper.isNumber(data)) {
            return new Transferable(data, State.NUMBER);
        }else if(Helper.isAllowString(data)){
            return new Transferable(data, State.UNIDENTIFIED_STR);
        }else if(Helper.isComma(data)){
            return new Transferable(data, State.COMMA);
        }else if(Helper.isOpMinus(data)){
            // 可能是减号或是负号
            return new Transferable(data, State.UNIDENTIFIED_OP_MINUS);
        }else if(Helper.isOp(data)){
            return new Transferable(data, State.OP);
        }else if(Helper.isRightBracket(data)){
            return new Transferable(data, State.RIGHT_BRACKET);
        }else if(Helper.isSingleQuotes(data)){
            return new Transferable(data, State.STR_START);
        }
        return null;
    }

    @Override
    public State current() {
        return State.BLANK_SPACE;
    }

}
