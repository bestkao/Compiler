package ast;

import visitor.*;

public class CallTree extends AST {

    public CallTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitCallTree(this);
    }

}

