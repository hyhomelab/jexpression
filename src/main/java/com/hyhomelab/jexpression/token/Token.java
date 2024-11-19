package com.hyhomelab.jexpression.token;

import java.util.Objects;

public record Token(TokenType tokenType,
                    String data,
                    int startPos,
                    int endPos) {

    @Override
    public String toString() {
        return String.format("[%s][%d:%d]%s",tokenType,startPos,endPos,data);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Token token = (Token) o;
        return endPos == token.endPos && startPos == token.startPos && Objects.equals(data, token.data) && tokenType == token.tokenType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tokenType, data, startPos, endPos);
    }
}
