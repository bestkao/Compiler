package ast;

import visitor.*;

public class DeclTree extends AST {

    public DeclTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitDeclTree(this);
    }

}

