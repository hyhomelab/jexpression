package com.hyhomelab.jexpression.functions;

import com.hyhomelab.jexpression.expression.nontermial.function.Library;
import com.hyhomelab.jexpression.functions.base.Between;
import com.hyhomelab.jexpression.functions.base.Choose;
import com.hyhomelab.jexpression.functions.base.IfFunc;
import com.hyhomelab.jexpression.functions.base.Not;
import com.hyhomelab.jexpression.functions.list.List;
import com.hyhomelab.jexpression.functions.math.*;
import com.hyhomelab.jexpression.functions.str.Repeat;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/11/21 17:10
 */
public class FuncLibraryLoader {

    public static Library[] loadDefaultLibraries(){
        return new Library[]{
                loadBase(),
                loadMath(),
                loadString(),
                loadList()
        };
    }

    public static Library loadBase(){
        var lib = new Library("基本");
        lib.loadFunction(
                new Choose(),
                new IfFunc(),
                new Not(),
                new Between()
        );
        return lib;
    }

    public static Library loadList(){
        var lib = new Library("集合");
        lib.loadFunction(
                new List()
        );
        return lib;
    }

    public static Library loadMath(){
        var lib = new Library("数学");
        lib.loadFunction(
                new Abs(),
                new Ceil(),
                new E(),
                new Floor(),
                new Pi(),
                new Power(),
                new Rand(),
                new RandBetween(),
                new Round(),
                new Sum(),
                new Max(),
                new Min()
        );
        return lib;
    }

    public static Library loadString(){
        var lib = new Library("字符串");
        lib.loadFunction(
                new Repeat()
        );
        return lib;
    }
}
