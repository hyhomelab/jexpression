package com.hyhomelab.jexpression.scanner;

public class Number extends AbsTransfer{
    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if (Helper.isBlankSpace(data)){
            return new Transferable(data, State.BLANK_SPACE);
        }else if (Helper.isNumberWithZero(data)){
            return new Transferable(data, State.NUMBER);
        }else if(Helper.isOp(data)){
            return new Transferable(data, State.OP);
        }else if(Helper.isPointOp(data)){
            // 数字后面跟着点，转变成小数
            return new Transferable(data, new TransferNext(State.DECIMAL, State.DECIMAL));
        }else if(Helper.isRightBracket(data)){
            return new Transferable(data, State.RIGHT_BRACKET);
        }else if(Helper.isComma(data)){
            return new Transferable(data, State.COMMA);
        }
        return null;
    }

    @Override
    public State current() {
        return State.NUMBER;
    }
}
