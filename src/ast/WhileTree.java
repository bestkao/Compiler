package ast;

import visitor.*;

public class WhileTree extends AST {

    public WhileTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitWhileTree(this);
    }
}

