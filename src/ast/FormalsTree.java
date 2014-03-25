package ast;

import visitor.*;

public class FormalsTree extends AST {

    public FormalsTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitFormalsTree(this);
    }

}

