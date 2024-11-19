package com.hyhomelab.jexpression.scanner;

/**
 * 小数
 */
public class Decimal extends AbsTransfer{
    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if (Helper.isBlankSpace(data)){
            return new Transferable(data, State.BLANK_SPACE);
        }else if(Helper.isAllowString(data)){
            return new Transferable(data, State.OP_KEY_WORD);
        }else if(Helper.isComma(data)){
            return new Transferable(data, State.COMMA);
        }else if(Helper.isRightBracket(data)){
            return new Transferable(data, State.RIGHT_BRACKET);
        }else if(Helper.isOp(data)){
            return new Transferable(data, State.OP);
        }else if(Helper.isNumberWithZero(data)){
            return new Transferable(data, State.DECIMAL);
        }
        return null;
    }

    @Override
    public State current() {
        return State.DECIMAL;
    }
}
