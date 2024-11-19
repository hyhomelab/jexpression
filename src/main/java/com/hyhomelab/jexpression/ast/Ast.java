package com.hyhomelab.jexpression.ast;

import com.hyhomelab.jexpression.exception.InvalidParameterException;
import com.hyhomelab.jexpression.expression.Expression;
import com.hyhomelab.jexpression.expression.nontermial.*;
import com.hyhomelab.jexpression.expression.terminal.DecimalExpression;
import com.hyhomelab.jexpression.expression.terminal.NumberExpression;
import com.hyhomelab.jexpression.expression.terminal.StringExpression;
import com.hyhomelab.jexpression.expression.terminal.VarExpression;
import com.hyhomelab.jexpression.token.Token;
import com.hyhomelab.jexpression.token.TokenType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Ast {

    private final Map<String, Integer> opLevelMap = new HashMap<>();
    private Stack<Token> opTokenStack = new Stack<>(); // 符号栈
    private AstStack<Expression> expStack = new AstStack<>(); // 表达式栈

    public Ast(){
        this.initOpLevelMap();
    }

    private void initOpLevelMap() {
       this.opLevelMap.put("(", 1);
       this.opLevelMap.put(")", 100);
       this.opLevelMap.put("func", 2);
       this.opLevelMap.put("*", 3);
       this.opLevelMap.put("/", 3);
       this.opLevelMap.put("+", 4);
       this.opLevelMap.put("-", 4);
       this.opLevelMap.put(">=", 5);
       this.opLevelMap.put("<=", 5);
       this.opLevelMap.put(">", 5);
       this.opLevelMap.put("<", 5);
       this.opLevelMap.put("==", 5);
       this.opLevelMap.put("in", 5);
       this.opLevelMap.put("and", 6);
       this.opLevelMap.put("or", 6);
       this.opLevelMap.put("&&", 6);
       this.opLevelMap.put("||", 6);
       this.opLevelMap.put(",", 7);
    }

    private Integer getTokenLevel(Token token) {
        if(token.tokenType() == TokenType.FUNC){
            return this.opLevelMap.get("func");
        }else if(token.tokenType() == TokenType.OP || token.tokenType() == TokenType.KEY_WORD){
            return this.opLevelMap.get(token.data());
        }
        return 9999;
    }

    /**
     * 根据 token流构建语法树
     * @param tokens
     * @return 根节点
     */
    public Expression parse(List<Token> tokens){
        if(tokens == null || tokens.isEmpty()){
            throw new InvalidParameterException("tokens is null or empty");
        }

        /**
         * 算法：
         * 符号入符号栈，符号优先级比栈顶高，直接入栈，比栈顶低的，一直弹出栈构建表达式节点入栈，直到优先级大于等于栈顶，再入栈
         */
        for(Token token : tokens){
            if(List.of(TokenType.START, TokenType.END).contains(token.tokenType())){
                continue;
            }else if(List.of(TokenType.NUMBER, TokenType.DECIMAL, TokenType.STRING, TokenType.VAR).contains(token.tokenType())){
                // 构建基础类型的表达式节点
                Expression exp = this.buildBaseTypeExpression(token);
                // 加入表达式栈
                this.expStack.push(exp);
            }else if(TokenType.FUNC == token.tokenType()) {
                this.opTokenStack.push(token);
                // 开辟方法表达式栈
                this.expStack.enterFuncStack();
            }else if(token.tokenType() == TokenType.LEFT_BRACKET) {
                this.opTokenStack.push(token);
            }else if(token.tokenType() == TokenType.RIGHT_BRACKET){
                // 处理右括号(不加入 opStack)
                // 判断当前是纯提升优先级，还是方法参数
                if(this.expStack.isFuncStack()){
                    this.opTokenStack.pop(); // 抛掉左括号
                    // 方法
                    Token funcToken = this.opTokenStack.pop();
                    // 退出方法表达式栈，提取参数
                    List<Expression> args = expStack.exitFuncStack();
                    Expression funcExp = this.buildFuncExpression(funcToken, args);
                    this.expStack.push(funcExp);
                }else{
                    // 纯提升优先级
                    // 弹出 op 构建表达式，直到遇到左括号
                    buildExpression();
                }
            }else if(token.tokenType() == TokenType.OP || token.tokenType() == TokenType.KEY_WORD){
                // 处理 KeyWord，Op，
                if(this.opTokenStack.isEmpty()){
                    this.opTokenStack.push(token);
                }else{
                    while(!this.opTokenStack.isEmpty() && !this.canPushToOpStack(token, this.opTokenStack.peek())){
                        // 优先级低的，弹出符号栈里的操作符，构建表达式
                        Token opToken = this.opTokenStack.pop();
                        Expression rightExp = this.expStack.pop(); // 先弹出的是运算符右边的表达式
                        Expression leftExp = this.expStack.pop();
                        Expression buildExp = this.buildOpExpression(opToken, leftExp, rightExp);
                        expStack.push(buildExp);
                    }
                    // 优先级比栈顶高的，直接入栈
                    this.opTokenStack.push(token);
                }
            }else{
                throw new InvalidParameterException("token type not supported");
            }
        }
        // 检查操作栈
        buildExpression();
        // 最后值栈里只会剩下一个根表达式
        assert expStack.size() == 1;
        return expStack.pop();
    }

    private void buildExpression() {
        while(!this.opTokenStack.isEmpty()){
            Token opToken = this.opTokenStack.pop();
            if(TokenType.LEFT_BRACKET == opToken.tokenType()){
                break;
            }
            if(TokenType.FUNC == opToken.tokenType()){
                // 只剩下一个 func 节点，根节点就是它了
                break;
            }
            Expression rightExp = this.expStack.pop(); // 先弹出的是运算符右边的表达式
            Expression leftExp = this.expStack.pop();
            Expression buildExp = this.buildOpExpression(opToken, leftExp, rightExp);
            this.expStack.push(buildExp);

        }
    }

    private Expression buildOpExpression(Token opToken, Expression leftExp, Expression rightExp) {
        return switch (opToken.data()){
            case "&&" -> new AndExpression(leftExp, rightExp);
            case "/" -> new DivExpression(leftExp, rightExp);
            case "==" -> new EqExpression(leftExp, rightExp);
            case "!=" -> new NotEqExpression(leftExp, rightExp);
            case ">=" -> new GeExpression(leftExp, rightExp);
            case ">" -> new GtExpression(leftExp, rightExp);
            case "in" -> new InExpression(leftExp, rightExp);
            case "<=" -> new LeExpression(leftExp, rightExp);
            case "<" -> new LtExpression(leftExp, rightExp);
            case "*" -> new MultiExpression(leftExp, rightExp);
            case "+" -> new PlusExpression(leftExp, rightExp);
            case "-" -> new SubExpression(leftExp, rightExp);
            case "func" -> new FuncExpression(opToken.data());
            default -> throw new InvalidParameterException("unknown op token: " + opToken.data());
        };
    }


    private Expression buildFuncExpression(Token token, List<Expression> args) {
        return new FuncExpression(token.data(), args.toArray(new Expression[0]));
    }

    private boolean canPushToOpStack(Token token, Token opStackTopToken){
        // 判断 keyword 和 op 的优先级

        int opStackTopTokenLv = this.getTokenLevel(opStackTopToken);
        int currentTokenLv = this.getTokenLevel(token);
        return currentTokenLv < opStackTopTokenLv; // 等级越低，优先级越高
    }

    private Expression buildBaseTypeExpression(Token token){
        return switch (token.tokenType()){
            case DECIMAL -> new DecimalExpression(token.data());
            case NUMBER -> new NumberExpression(token.data());
            case STRING -> new StringExpression(token.data());
            case VAR -> new VarExpression(token.data());
            default -> throw new InvalidParameterException("unsupported token type");
        };
    }
}
