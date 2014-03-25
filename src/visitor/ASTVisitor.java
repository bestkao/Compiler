package visitor;

import ast.*;

/**
 *  ASTVisitor class is the root of the Visitor hierarchy for visiting
 *  various AST's; each visitor asks each node in the AST it is given
 *  to <i>accept</i> its visit; <br>
 *  each subclass <b>must</b> provide all of the visitors mentioned
 *  in this class; <br>
 *  after visiting a tree the visitor can return any Object of interest<br>
 *  e.g. when the constrainer visits an expression tree it will return
 *  a reference to the type tree representing the type of the expression
*/
public abstract class ASTVisitor {

    public void visitKids(AST t) {
        for (AST kid : t.getKids()) {
            kid.accept(this);
        }
        return;
    }

    public abstract Object visitProgramTree(AST t);
    public abstract Object visitBlockTree(AST t);
    public abstract Object visitFunctionDeclTree(AST t);
    public abstract Object visitCallTree(AST t);
    public abstract Object visitDeclTree(AST t);
    public abstract Object visitIntTypeTree(AST t);
    public abstract Object visitFloatTypeTree(AST t);
    public abstract Object visitBoolTypeTree(AST t);
    public abstract Object visitFormalsTree(AST t);
    public abstract Object visitActualArgsTree(AST t);
    public abstract Object visitIfTree(AST t);
    public abstract Object visitWhileTree(AST t);
    public abstract Object visitReturnTree(AST t);
    public abstract Object visitAssignTree(AST t);
    public abstract Object visitRepeatTree(AST t);
    public abstract Object visitIntTree(AST t);
    public abstract Object visitFloatTree(AST t);
    public abstract Object visitIdTree(AST t);
    public abstract Object visitRelOpTree(AST t);
    public abstract Object visitAddOpTree(AST t);
    public abstract Object visitMultOpTree(AST t);
}