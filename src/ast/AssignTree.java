package ast;

import visitor.*;

public class AssignTree extends AST {

    public AssignTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitAssignTree(this);
    }

}

