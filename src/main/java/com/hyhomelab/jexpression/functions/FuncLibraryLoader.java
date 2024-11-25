package com.hyhomelab.jexpression.functions;

import com.hyhomelab.jexpression.expression.nontermial.function.Library;
import com.hyhomelab.jexpression.functions.base.Choose;
import com.hyhomelab.jexpression.functions.base.IfElse;
import com.hyhomelab.jexpression.functions.base.Not;
import com.hyhomelab.jexpression.functions.list.List;
import com.hyhomelab.jexpression.functions.math.Sum;
import com.hyhomelab.jexpression.functions.str.StringRepeat;

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
        var lib = new Library("base");
        lib.loadFunction(
                new Choose(),
                new IfElse(),
                new Not()
        );
        return lib;
    }

    public static Library loadList(){
        var lib = new Library("list");
        lib.loadFunction(
                new List()
        );
        return lib;
    }

    public static Library loadMath(){
        var lib = new Library("math");
        lib.loadFunction(
                new Sum()
        );
        return lib;
    }

    public static Library loadString(){
        var lib = new Library("string");
        lib.loadFunction(
                new StringRepeat()
        );
        return lib;
    }
}
