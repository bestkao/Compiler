package ast;

import visitor.*;

public class FloatTypeTree extends AST {

    public FloatTypeTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitFloatTypeTree(this);
    }

}

