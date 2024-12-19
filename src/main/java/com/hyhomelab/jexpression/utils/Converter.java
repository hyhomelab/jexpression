package com.hyhomelab.jexpression.utils;

import java.math.BigDecimal;

/**
 * 表达式执行结果类型转换器
 */
public class Converter {

    private final Object value;

    public Converter(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    public String toString(){
        return value.toString();
    }

    public int toInt(){
        if(this.value instanceof Number){
            return ((Number) this.value).intValue();
        }
        return Integer.parseInt(this.value.toString());
    }

    public long toLong(){
        if(this.value instanceof Number){
            return ((Number) this.value).longValue();
        }
        return Long.parseLong(this.value.toString());
    }

    public float toFloat(){
        if(this.value instanceof Number){
            return ((Number) this.value).floatValue();
        }
        return Float.parseFloat(this.value.toString());
    }

    public double toDouble(){
        if(this.value instanceof Number){
            return ((Number) this.value).doubleValue();
        }
        return Double.parseDouble(this.value.toString());
    }
    public boolean toBoolean(){
        if(this.value instanceof Boolean){
            return (Boolean) this.value;
        }
        return Boolean.parseBoolean(this.value.toString());
    }

    public BigDecimal toBigDecimal(){
        if(this.value instanceof BigDecimal){
            return (BigDecimal) this.value;
        }else if(this.value instanceof Number){
            return new BigDecimal(this.value.toString());
        }
        return new BigDecimal(this.value.toString());
    }
}
