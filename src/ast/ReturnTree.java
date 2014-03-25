package ast;

import visitor.*;

public class ReturnTree extends AST {

    public ReturnTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitReturnTree(this);
    }

}

