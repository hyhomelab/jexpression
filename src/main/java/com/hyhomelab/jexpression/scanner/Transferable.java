package com.hyhomelab.jexpression.scanner;

public class Transferable {
    private final char data;
    private final TransferNext next;

    public Transferable(char data, State next) {
        this.data = data;
        this.next = new TransferNext(next);
    }

    public Transferable( char data, TransferNext next) {
        this.data = data;
        this.next = next;
    }


    public char getData() {
        return data;
    }

    public TransferNext getNext() {
        return next;
    }
}
