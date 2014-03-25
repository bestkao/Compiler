package ast;

import visitor.*;

public class ProgramTree extends AST {

    public ProgramTree() {
    }

    public Object accept(ASTVisitor v) {
        return v.visitProgramTree(this);
    }

}

