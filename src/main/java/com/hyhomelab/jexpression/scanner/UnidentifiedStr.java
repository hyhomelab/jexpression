package com.hyhomelab.jexpression.scanner;

/**
 * 未定义的字符串,属于中间态，可能是方法名，可能是变量，可能是关键字
 */
public class UnidentifiedStr extends AbsTransfer{


    @Override
    public State current() {
        return State.UNIDENTIFIED_STR;
    }

    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if (Helper.isAllowString(data) || Helper.isNumberWithZero(data)){
            return new Transferable(data, State.UNIDENTIFIED_STR);
        }else if(Helper.isOp(data)) {
            return new Transferable(data, new TransferNext(State.OP,State.VAR));
        }else if(Helper.isComma(data)){
            return new Transferable(data, new TransferNext(State.COMMA,State.VAR));
        }else if(Helper.isLeftBracket(data)) {
            return new Transferable(data, new TransferNext(State.LEFT_BRACKET, State.FUNC));
        }else if(Helper.isRightBracket(data)){
            return new Transferable(data, new TransferNext(State.RIGHT_BRACKET, State.VAR));
        }else if(Helper.isBlankSpace(data)){
            if (Helper.isKeyWord(ctx.getCurrentWord())) {
                // key word
                return new Transferable(data, new TransferNext(State.BLANK_SPACE, State.OP_KEY_WORD));
            }else{
                // var
                return new Transferable( data, new TransferNext(State.BLANK_SPACE, State.VAR));
            }
        }
        return null;
    }
}
