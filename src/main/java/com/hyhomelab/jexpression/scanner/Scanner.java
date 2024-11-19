package com.hyhomelab.jexpression.scanner;

import com.hyhomelab.jexpression.exception.ScannerException;
import com.hyhomelab.jexpression.token.Token;
import com.hyhomelab.jexpression.token.TokenType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Scanner {


    private final Map<State, Transfer> stateTransferMap = new HashMap<>();

    public Scanner() {
        this.initStateTransferMap();
    }

    public void initStateTransferMap() {
        // 初始化状态转移
        this.stateTransferMap.put(State.NUMBER, new Number());
        this.stateTransferMap.put(State.OP, new Op());
        this.stateTransferMap.put(State.LEFT_BRACKET, new LeftBracket());
        this.stateTransferMap.put(State.RIGHT_BRACKET, new RightBracket());
        this.stateTransferMap.put(State.ZERO, new Zero());
        this.stateTransferMap.put(State.DECIMAL, new Decimal());
        this.stateTransferMap.put(State.POINT_OP, new PointOp());
        this.stateTransferMap.put(State.FUNC, new Func());
        this.stateTransferMap.put(State.COMMA, new Comma());
        this.stateTransferMap.put(State.UNIDENTIFIED_STR, new UnidentifiedStr());
        this.stateTransferMap.put(State.STR_START, new StrStart());
        this.stateTransferMap.put(State.STR, new Str());
        this.stateTransferMap.put(State.STR_END, new StrEnd());
        this.stateTransferMap.put(State.VAR, new Var());
        this.stateTransferMap.put(State.OP_KEY_WORD, new OpKeyWord());
        this.stateTransferMap.put(State.START, new Start());
        this.stateTransferMap.put(State.BLANK_SPACE, new BlankSpace());
        this.stateTransferMap.put(State.UNIDENTIFIED_OP_MINUS, new UnidentifiedOpMinus());
    }

    public List<Token> scan(String expression){
        if (expression == null || expression.isEmpty()) {
            return new ArrayList<>();
        }
        // 初始化
        State lastState = State.START; // 上个状态
        StringBuilder currentData = new StringBuilder();
        List<Token> tokens = new ArrayList<>();
        int currentPos = 0;
        int startPos = 0;
        Context ctx = new Context();
        expression = expression + " "; // 补个空格，方便最后一个 state 的确认
        do{
            char c = expression.charAt(currentPos);
            Transfer transfer = this.stateTransferMap.get(lastState);
            try {
                // 传入当前累积的字符串
                ctx.setCurrentWord(currentData.toString());
                ctx.setTokens(tokens);
                // 尝试进行状态转移
                Transferable transferable = transfer.tryTo(ctx, c, lastState);
                TransferNext next = transferable.getNext();
                // 处于中间态的，替换成新状态
                if(next.isReplace()){
                    lastState = next.getReplaceState();
                }
                if ( lastState != next.getNext()){

                    if(lastState != State.BLANK_SPACE
                            && lastState != State.COMMA
                            && lastState != State.STR_START
                            && lastState != State.STR_END){
                        // 添加到 tokens 里
                        tokens.add(this.buildToken(lastState, currentData.toString(), startPos, currentPos));
                    }

                    // 状态转移后处理
                    currentData = new StringBuilder();
                    startPos = currentPos;
                }

                currentData.append(c);
                // 转移状态
                lastState = next.getNext();

            }catch (Exception e){
                throwException(e, expression, c, startPos, currentPos);
            }
            currentPos++;
        }while(currentPos < expression.length());

        // 添加结束符号，endPos 需要扣掉多加的那个空格
        tokens.add(this.buildToken(State.END, "", startPos, currentPos - 1));
        return tokens;
    }

    private void throwException(Exception e, String expression, char c, int startPos, int endPos) {
        var spaceBuilder = new StringBuilder().repeat(' ', startPos).append('^');
        throw new ScannerException(String.format("%s \nchar '%s' at pos:(%d, %d) is invalid! \n%s \n%s",e, c, startPos, endPos, expression, spaceBuilder));
    }


    private Token buildToken(State state, String data, int startPos, int endPos) {
        var tokenType = switch (state){
            case ZERO, NUMBER ->TokenType.NUMBER;
            case OP -> TokenType.OP;
            case OP_KEY_WORD -> TokenType.KEY_WORD;
            case LEFT_BRACKET -> TokenType.LEFT_BRACKET;
            case RIGHT_BRACKET -> TokenType.RIGHT_BRACKET;
            case DECIMAL -> TokenType.DECIMAL;
            case FUNC -> TokenType.FUNC;
//            case COMMA -> TokenType.COMMA;
            case STR, STR_END -> TokenType.STRING;
            case VAR -> TokenType.VAR;
            case END -> TokenType.END;
            case START -> TokenType.START;
            default -> throw new IllegalStateException("Unexpected state: " + state);
        };
        return new Token(tokenType, data, startPos, endPos);
    }
}
