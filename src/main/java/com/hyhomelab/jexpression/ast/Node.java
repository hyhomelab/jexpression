package com.hyhomelab.jexpression.ast;

import com.hyhomelab.jexpression.token.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 语法树节点
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 14:40
 */
public class Node {
    private final Token token;
    private final List<Node> childrenNodes;

    public Node(Token token) {
        this.token = token;
        this.childrenNodes = new ArrayList<>();
    }

    public Token getToken() {
        return token;
    }

    public Node addNodes(Node... node) {
        childrenNodes.addAll(List.of(node));
        return this;
    }

    public List<Node> getChildrenNodes() {
        return childrenNodes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(token, node.token) && Objects.equals(childrenNodes, node.childrenNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token, childrenNodes);
    }
}
