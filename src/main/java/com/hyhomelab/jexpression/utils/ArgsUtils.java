package com.hyhomelab.jexpression.utils;

public class ArgsUtils {
    public static Converter getArg(Object[] args, int index) {
        if (args == null || index < 0 || index >= args.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + args.length);
        }
        var arg = args[index];
        return new Converter(arg);
    }

}
