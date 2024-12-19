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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ast {

    private final Map<String, Integer> opLevelMap = new HashMap<>();
    private final AstStack<Token> opTokenStack = new AstStack<>(); // 符号栈
    private final AstStack<Node> nodeStack = new AstStack<>(); // 节点栈

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
     * 构建抽象语法树
     * @param tokens
     * @return
     */
    public Node buildTree(List<Token> tokens) {
        if(tokens == null || tokens.isEmpty()){
            throw new InvalidParameterException("tokens is null or empty");
        }

        for(Token token : tokens){
            if(List.of(TokenType.START, TokenType.END).contains(token.tokenType())){
                continue;
            }else if(List.of(TokenType.NUMBER, TokenType.DECIMAL, TokenType.STRING, TokenType.VAR).contains(token.tokenType())){
                // 构建基础类型的表达式节点
                Node node = new Node(token);
                // 加入表达式栈
                this.nodeStack.push(node);
            }else if(TokenType.FUNC == token.tokenType()) {
                this.opTokenStack.push(token);
            }else if(token.tokenType() == TokenType.LEFT_BRACKET) {
                // 开辟新栈
                this.nodeStack.deepStack();
                this.opTokenStack.deepStack();
            }else if(token.tokenType() == TokenType.RIGHT_BRACKET) {
                // 处理右括号(不加入 opStack)
                // 构建括号里的表达式
                buildNode();
                if(this.opTokenStack.isInDeep()){
                    //
                    this.opTokenStack.leaveStack();
                }
                if(this.opTokenStack.peek() != null){
                    if(TokenType.FUNC == this.opTokenStack.peek().tokenType()){

                        // 括号里是方法参数
                        Token funcToken = this.opTokenStack.pop();
                        // 退出方法表达式栈，提取参数
                        List<Node> args = nodeStack.leaveStack();
                        Node node = new Node(funcToken);
                        node.addNodes(args.toArray(new Node[0]));
                        this.nodeStack.push(node);
                        continue;
                    }
                }
                // 如果不是方法调用，就是优先级，里面只可能剩下一个节点
                if(!this.nodeStack.isEmpty()){
                    var node = this.nodeStack.pop();
                    this.nodeStack.leaveStack();
                    this.nodeStack.push(node);
                }

            }else if(List.of(TokenType.OP ,TokenType.KEY_WORD, TokenType.COMMA).contains(token.tokenType())){
                // 处理 KeyWord，Op，
                if(this.opTokenStack.isEmpty()){
                    this.opTokenStack.push(token);
                }else{
                    while(!this.opTokenStack.isEmpty() && !this.canPushToOpStack(token, this.opTokenStack.peek())){
                        // 优先级低的，弹出符号栈里的操作符，构建表达式
                        Token opToken = this.opTokenStack.pop();
                        if(TokenType.COMMA == opToken.tokenType()){
                            break;
                        }
                        Node rightNode = this.nodeStack.pop(); // 先弹出的是运算符右边的表达式
                        Node leftNode = this.nodeStack.pop();
                        Node node = new Node(opToken);
                        node.addNodes(leftNode, rightNode);
                        nodeStack.push(node);
                    }
                    // 优先级比栈顶高的，直接入栈
                    this.opTokenStack.push(token);
                }
            }else{
                throw new InvalidParameterException("token type not supported");
            }
        }
        // 检查操作栈
        buildNode();
        // 最后值栈里只会剩下一个根表达式
        assert nodeStack.size() == 1;
        return nodeStack.pop();
    }

    /**
     * 把语法树转换成解释器结构的表达式
     * @param root
     * @return
     */
    public Expression treeNodeToExpression(Node root){
        // 深度遍历所有节点，构建 expression
        Token token = root.getToken();
        if(List.of(TokenType.NUMBER, TokenType.DECIMAL, TokenType.STRING, TokenType.VAR).contains(token.tokenType())){

            // 构建基础类型的表达式节点
            return this.buildBaseTypeExpression(token);
        }else if(TokenType.FUNC == token.tokenType()) {

            // 构建方法表达式节点
            List<Expression> args = new ArrayList<>();
            for(Node node : root.getChildrenNodes()){
                var exp = this.treeNodeToExpression(node);
                args.add(exp);
            }
            return this.buildFuncExpression(root.getToken(), args);
        }else if(List.of(TokenType.OP ,TokenType.KEY_WORD).contains(token.tokenType())){

            // 构建二元操作符
            Node left = root.getChildrenNodes().get(0);
            Node right = root.getChildrenNodes().get(1);
            Expression leftExp = this.treeNodeToExpression(left);
            Expression rightExp = this.treeNodeToExpression(right);
            return this.buildOpExpression(root.getToken(), leftExp, rightExp);
        }else{
            throw new InvalidParameterException("token type not supported");
        }
    }

    /**
     * 根据 token流构建语法树
     * @param tokens
     * @return 根节点
     */
    public Expression parse(List<Token> tokens){
        // 构建抽象语法树
        Node root = buildTree(tokens);
        // 翻译成解释器
        Expression rootExp = this.treeNodeToExpression(root);
        return rootExp;
    }

    private void buildNode() {
        while(!this.opTokenStack.isEmpty()){
            Token opToken = this.opTokenStack.pop();
            if(TokenType.OP == opToken.tokenType() || TokenType.KEY_WORD == opToken.tokenType()) {
                // op
                Node rightNode = this.nodeStack.pop(); // 先弹出的是运算符右边的表达式
                Node leftNode = this.nodeStack.pop();
                Node node = new Node(opToken);
                node.addNodes(leftNode, rightNode);
                this.nodeStack.push(node);
            }else if(TokenType.COMMA == opToken.tokenType()){
                continue;
            }else{
                throw new InvalidParameterException("token type not supported");
            }
        }
    }


    private Expression buildOpExpression(Token opToken, Expression leftExp, Expression rightExp) {
        return switch (opToken.data()){
            case "&&","and" -> new AndExpression(leftExp, rightExp);
            case "||", "or" -> new OrExpression(leftExp, rightExp);
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
