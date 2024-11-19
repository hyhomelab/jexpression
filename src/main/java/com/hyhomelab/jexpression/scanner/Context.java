package com.hyhomelab.jexpression.scanner;

import com.hyhomelab.jexpression.token.Token;

import java.util.List;

public class Context {

    private String currentWord;
    private List<Token> tokens;

    public String getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(String currentWord) {
        this.currentWord = currentWord;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
}
