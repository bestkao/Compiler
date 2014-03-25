package ast;

import visitor.*;

public class RepeatTree extends AST {

    public RepeatTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitRepeatTree(this);
    }
}
