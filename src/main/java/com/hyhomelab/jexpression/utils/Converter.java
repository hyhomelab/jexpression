package com.hyhomelab.jexpression.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public List<BigDecimal> toBigDecimalList(){
        var data = new ArrayList<BigDecimal>();
        if(this.value instanceof List){
            for(var row : (List<?>)this.value){
                if(row instanceof String){
                    data.add(new BigDecimal(String.valueOf(row)));
                }else if(row instanceof Long){
                    data.add(BigDecimal.valueOf((Long) row));
                }else if(row instanceof Integer){
                    data.add(BigDecimal.valueOf((Integer) row));
                }else if(row instanceof Double){
                    data.add(BigDecimal.valueOf((Double) row));
                }else{
                    data.add((BigDecimal) row);
                }
            }
        }
        return data;
    }

    public List<String> toStringList(){
        var data = new ArrayList<String>();
        if(this.value instanceof List){
            for(var row : (List<?>)this.value){
                if(row instanceof String){
                    data.add((String)row);
                }else{
                    data.add(row.toString());
                }
            }
        }
        return data;
    }
}
