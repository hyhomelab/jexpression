package com.hyhomelab.jexpression.ast;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class AstStack<T> {
    private final Stack<Stack<T>> containerStack = new Stack<>();

    public AstStack(){
        // 初始化就 push 一个栈进去作为默认栈
        this.containerStack.push(new Stack<T>());
    }

    public int size(){
        return this.containerStack.peek().size();
    }

    public void push(T item) {
        this.containerStack.peek().push(item);
    }

    public T pop() {
        return this.containerStack.peek().pop();
    }

    public T peek() {
        return this.containerStack.peek().peek();
    }

    public boolean isEmpty() {
        return this.containerStack.peek().isEmpty();
    }

    public boolean isFuncStack(){
        return this.containerStack.size() > 1;
    }

    public void enterFuncStack(){
        this.containerStack.push(new Stack<>());
    }

    /**
     *  退出方法表达式栈，并返回所有参数
     * @return 参数，按正常顺序排列
     */

    public List<T> exitFuncStack(){
        var stack = this.containerStack.pop();
        List<T> result = new ArrayList<>();
        while(stack != null && !stack.isEmpty()){
            result.add(stack.pop());
        }
        return result.reversed();
    }

}
