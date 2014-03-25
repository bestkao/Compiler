package ast;

import visitor.*;

public class BoolTypeTree extends AST {

    public BoolTypeTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitBoolTypeTree(this);
    }

}

