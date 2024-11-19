package com.hyhomelab.jexpression.scanner;

public abstract class AbsTransfer implements Transfer{


    protected abstract Transferable to(Context ctx, char data, State from);

    @Override
    public Transferable tryTo(Context ctx, char data, State from) {
        var result = this.to(ctx, data, from);
        if(result == null){
            throw new UnsupportedOperationException(String.format("[%s]char %s", this.current(), data));
        }
        return result;
    }

}
