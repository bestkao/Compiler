package ast;

import visitor.*;

public class FunctionDeclTree extends AST {

    public FunctionDeclTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitFunctionDeclTree(this);
    }

}

