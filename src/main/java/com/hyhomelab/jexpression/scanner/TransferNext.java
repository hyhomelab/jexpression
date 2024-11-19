package com.hyhomelab.jexpression.scanner;

public class TransferNext {
    private final State next;
    private final boolean replace;
    private final State replaceState;

    public TransferNext(State next) {
        this.next = next;
        this.replace = false;
        this.replaceState = null;
    }

    public TransferNext(State next, State replaceState) {
        this.next = next;
        this.replace = true;
        this.replaceState = replaceState;
    }


    public State getNext() {
        return this.next;
    }

    public boolean isReplace() {
        return this.replace;
    }

    public State getReplaceState() {
        return this.replaceState;
    }

    @Override
    public String toString() {
        return next.toString();
    }
}
