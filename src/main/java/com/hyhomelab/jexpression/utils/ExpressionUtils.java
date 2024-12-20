package com.hyhomelab.jexpression.utils;

import com.hyhomelab.jexpression.ast.Ast;
import com.hyhomelab.jexpression.ast.Node;
import com.hyhomelab.jexpression.scanner.Scanner;
import com.hyhomelab.jexpression.token.TokenType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/19 15:59
 */
public class ExpressionUtils {

    /**
     * 查找公式中的所有变量名称
     * @param expression 公式
     * @return 变量列表
     */
    public static List<String> findVars(String expression) {
        Scanner s = new Scanner();
        var tokens = s.scan(expression);

        Ast ast = new Ast();
        var root = ast.buildTree(tokens);

        List<Node> varNodes = findVarNodes(root);
        return varNodes.stream().map(node -> node.getToken().data()).distinct().collect(Collectors.toList());
    }


    private static List<Node> findVarNodes(Node root){
        List<Node> varNodes = new ArrayList<>();
        // 深度遍历节点抽取所有变量节点
        if (root.getToken().tokenType() == TokenType.VAR){
            varNodes.add(root);
        }
        for(var node : root.getChildrenNodes()){
            var nodes = findVarNodes(node);
            if (!nodes.isEmpty()){
                varNodes.addAll(nodes);
            }
        }
        return varNodes;
    }


}
