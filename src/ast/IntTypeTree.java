package ast;

import visitor.*;

public class IntTypeTree extends AST {

    public IntTypeTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitIntTypeTree(this);
    }

}

