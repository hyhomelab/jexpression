package com.hyhomelab.jexpression.scanner;

import com.hyhomelab.jexpression.token.TokenType;

import java.util.List;

/**
 * 不确定的减号
 */
public class UnidentifiedOpMinus extends AbsTransfer{

    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if(Helper.isBlankSpace(data)){
            return new Transferable(data, new TransferNext(State.BLANK_SPACE, State.OP));
        }else if(Helper.isNumberWithZero(data)){
            var lastToken = ctx.getTokens().get(ctx.getTokens().size() - 1);
            // 根据上个 token 判断这里的负号
            if(List.of(TokenType.OP.value(),
                    TokenType.COMMA.value(),
                    TokenType.LEFT_BRACKET.value(),
                    TokenType.KEY_WORD.value()).contains(lastToken.tokenType().value())){
                return new Transferable(data, new TransferNext(State.NUMBER, State.NUMBER));
            }else{
                return new Transferable(data, new TransferNext(State.NUMBER, State.OP));
            }
        }else if(Helper.isAllowString(data)){
            return new Transferable(data, new TransferNext(State.UNIDENTIFIED_STR, State.OP));
        }
        return null;
    }

    @Override
    public State current() {
        return State.UNIDENTIFIED_OP_MINUS;
    }
}
