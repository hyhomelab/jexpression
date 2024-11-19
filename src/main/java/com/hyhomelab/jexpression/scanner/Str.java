package com.hyhomelab.jexpression.scanner;

public class Str extends AbsTransfer{
    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if(Helper.isSingleQuotes(data)){
            return new Transferable(data, State.STR_END);
        }else{
            // 万物皆可 string
            return new Transferable(data, State.STR);
        }
        // todo 考虑增加转义功能（加个过度状态）
    }

    @Override
    public State current() {
        return State.STR;
    }
}
