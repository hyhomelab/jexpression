package com.hyhomelab.jexpression.scanner;

public class PointOp extends  AbsTransfer{
    @Override
    protected Transferable to(Context ctx, char data, State from) {
        if(Helper.isNumberWithZero(data)){
            return new Transferable(data, new TransferNext(State.DECIMAL, State.DECIMAL));
        }
        return null;
    }

    @Override
    public State current() {
        return State.POINT_OP;
    }
}
