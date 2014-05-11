package constrain;

import lexer.*;
import parser.Parser;
import visitor.*;
import ast.*;
import java.util.*;

/**
 *  Constrainer object will visit the AST, gather/check variable
 *  type information and decorate uses of variables with their
 *  declarations; the decorations will be used by the code generator
 *  to provide access to the frame offset of the variable for generating
 *  load/store bytecodes; <br>
 *  Note that when constraining expression trees we return the type tree
 *  corresponding to the result type of the expression; e.g. 
 *  the result of constraining the tree for 1+2*3 will be the int type
 *  tree
*/
public abstract class Constrainer extends ASTVisitor {
	public enum ConstrainerErrors {
	    BadAssignmentType, CallingNonFunction, ActualFormalTypeMismatch, NumberActualsFormalsDiffer, TypeMismatchInExpr,
	    BooleanExprExpected, BadConditional, ReturnNotInFunction, BadReturnExpr
	}

    

    private AST t;           // the AST to constrain
    private Table symtab = new Table();
    private Parser parser;   // parser used with this constrainer
    
/**
 *  The following comment refers to the functions stack
 *  declared below the comment.
 *  Whenever we start constraining a function declaration
 *  we push the function decl tree which indicates we're
 *  in a function (to ensure that we don't attempt to return
 *  from the main program - return's are only allowed from
 *  within functions); it also gives us access to the return
 *  type to ensure the type of the expr that is returned is
 *  the same as the type declared in the function header
*/
    private Stack<AST> functions = new Stack<AST>();

/**
 *  readTree, writeTree, intTree, boolTree,falseTree, trueTree
 *  are AST's that will be constructed (intrinsic trees) for
 *  every program. They are constructed in the same fashion as
 *  source program trees to ensure consisten processing of 
 *  functions, etc.
*/
    public static AST readTree, writeTree, intTree, boolTree,
               falseTree, trueTree, readId, writeId;
               
    public Constrainer(AST t, Parser parser) {
        this.t = t;
        this.parser = parser;
    }
    
    public void execute() {
        symtab.beginScope();
        t.accept(this);
    }

/**
 *  t is an IdTree; retrieve the pointer to its declaration
*/
    private AST lookup(AST t) {  
        return (AST)(symtab.get( ((IdTree)t).getSymbol()));
    }

/**
 *  Decorate the IdTree with the given decoration - its decl tree
*/
    private void enter(AST t, AST decoration) {
/*
        System.out.println("enter: "+((IdTree)t).getSymbol().toString()
              + ": " + decoration.getNodeNum());
*/
        symtab.put( ((IdTree)t).getSymbol(), decoration);
    }

/**
 *  get the type of the current type tree
 *  @param t is the type tree
 *  @return the intrinsic tree corresponding to the type of t
*/
    private AST getType(AST t) {
        return (t.getClass() == IntTypeTree.class)? intTree: boolTree;
    }

    public void decorate(AST t, AST decoration) {
        t.setDecoration(decoration);
    }

/**
 *  @return the decoration of the tree
*/
    public AST decoration(AST t) {
        return t.getDecoration();
    }
    
/**
 *  build the intrinsic trees; constrain them in the same fashion
 *  as any other AST
*/
    private void buildIntrinsicTrees() {
        Lexer lex = parser.getLex();
        trueTree = new IdTree(lex.newIdToken("true",-1,-1));
        falseTree = new IdTree(lex.newIdToken("false",-1,-1));
        readId = new IdTree(lex.newIdToken("read",-1,-1));
        writeId = new IdTree(lex.newIdToken("write",-1,-1));
        boolTree = (new DeclTree()).addKid(new BoolTypeTree()).
                 addKid(new IdTree(lex.newIdToken("<<bool>>",-1,-1)));
        decorate(boolTree.getKid(2),boolTree);  
        intTree = (new DeclTree()).addKid(new IntTypeTree()).
                 addKid(new IdTree(lex.newIdToken("<<int>>",-1,-1)));
        decorate(intTree.getKid(2),intTree);  
        // to facilitate type checking; this ensures int decls and id decls
        // have the same structure
        
        // read tree takes no parms and returns an int
        readTree = (new FunctionDeclTree()).addKid(new IntTypeTree()).
                       addKid(readId).addKid(new FormalsTree()).
                       addKid(new BlockTree());
        
        // write tree takes one int parm and returns that value
        writeTree = (new FunctionDeclTree()).addKid(new IntTypeTree()).
                       addKid(writeId);
        AST decl = (new DeclTree()).addKid(new IntTypeTree()).
                       addKid(new IdTree(lex.newIdToken("dummyFormal",-1,-1)));
        AST formals = (new FormalsTree()).addKid(decl);
        writeTree.addKid(formals).addKid(new BlockTree());
        writeTree.accept(this);
        readTree.accept(this);
    }
    
/**
 *  Constrain the program tree - visit its kid
*/
    public Object visitProgramTree(AST t) {
        buildIntrinsicTrees();
        this.t = t;
        t.getKid(1).accept(this);
        return null;
    }

/**
 *  Constrain the Block tree:<br>
 *  <ol><li>open a new scope, <li>constrain the kids in this new scope, <li>close the
 *  scope removing any local declarations from this scope</ol>
*/
    public Object visitBlockTree(AST t) {
        symtab.beginScope();
        visitKids(t);
        symtab.endScope();
        return null; }
        
/**
 *  Constrain the FunctionDeclTree:
 *  <ol><li>Enter the function name in the current scope, <li>enter the formals
 *  in the function scope and <li>constrain the body of the function</ol>
*/
    public Object visitFunctionDeclTree(AST t) {
        AST fname = t.getKid(2),
            returnType = t.getKid(1),
            formalsTree = t.getKid(3),
            bodyTree = t.getKid(4);
        functions.push(t);
        enter(fname,t);  // enter function name in CURRENT scope
        decorate(returnType,getType(returnType));
        symtab.beginScope();  // new scope for formals and body
        visitKids(formalsTree); // all formal names go in new scope
        bodyTree.accept(this);
        symtab.endScope();
        functions.pop();
        return null;
    }
        
/**
 *  Constrain the Call tree:<br>
 *  check that the number and types of the actuals match the
 *  number and type of the formals
*/
    public Object visitCallTree(AST t) {
        AST fct,
            fname = t.getKid(1),
            fctType;
        visitKids(t);
        fct = lookup(fname);
        if (fct.getClass() != FunctionDeclTree.class) {
            constraintError(ConstrainerErrors.CallingNonFunction);
        }
        fctType = decoration(fct.getKid(1));
        decorate(t,fctType);
        decorate(t.getKid(1),fct);
        // now check that the number/types of actuals match the
        // number/types of formals
        checkArgDecls(t,fct);
        return fctType;
    }
    
    private void checkArgDecls(AST caller, AST fct) {
        // check number and types of args/formals match
        AST formals = fct.getKid(3);
        Iterator<AST> actualKids = caller.getKids().iterator(),
                    formalKids = formals.getKids().iterator();
        actualKids.next();  // skip past fct name
        for (; actualKids.hasNext();) {
            try {
                AST actualDecl = decoration(actualKids.next()),
                    formalDecl = formalKids.next();
                if (decoration(actualDecl.getKid(2)) != 
                    decoration(formalDecl.getKid(2))) {
                    constraintError(ConstrainerErrors.ActualFormalTypeMismatch);
                }
            } catch (Exception e) {
                constraintError(ConstrainerErrors.NumberActualsFormalsDiffer);
            }
        }
        if (formalKids.hasNext()) {
            constraintError(ConstrainerErrors.NumberActualsFormalsDiffer);
        }
        return;
    }
                
/**
 *  Constrain the Decl tree:<br>
 *  <ol><li>decorate to the corresponding intrinsic type tree, <li>enter the
 *  variable in the current scope so later variable references can
 *  retrieve the information in this tree</ol>
*/
    public Object visitDeclTree(AST t) {
        AST idTree = t.getKid(2);
        enter(idTree,t);
        AST typeTree = getType(t.getKid(1));
        decorate(idTree,typeTree);
        return null; }
        
/**
 *  Constrain the <i>If</i> tree:<br>
 *  check that the first kid is an expression that is a boolean type
*/
    public Object visitIfTree(AST t) {
        if ( t.getKid(1).accept(this) != boolTree) {
            constraintError(ConstrainerErrors.BadConditional);
        }
        t.getKid(2).accept(this);
        t.getKid(3).accept(this);
        return null;
    }
        
    public Object visitWhileTree(AST t) {
        if ( t.getKid(1).accept(this) != boolTree) {
            constraintError(ConstrainerErrors.BadConditional);
        }
        t.getKid(2).accept(this);
        return null;
    }
        
/**
 *  Constrain the Return tree:<br>
 *  Check that the returned expression type matches the type indicated
 *  in the function we're returning from
*/
    public Object visitReturnTree(AST t) {
        if (functions.empty()) {
            constraintError(ConstrainerErrors.ReturnNotInFunction);
        }
        AST currentFunction = (functions.peek());
        decorate(t,currentFunction);
        AST returnType = decoration(currentFunction.getKid(1));
        if ( (t.getKid(1).accept(this)) != returnType) {
            constraintError(ConstrainerErrors.BadReturnExpr);
        }
        return null;
    }
        
/**
 *  Constrain the Assign tree:<br>
 *  be sure the types of the right-hand-side expression and variable
 *  match; when we constrain an expression we'll return a reference
 *  to the intrinsic type tree describing the type of the expression
*/
    public Object visitAssignTree(AST t) {
        AST idTree = t.getKid(1),
            idDecl = lookup(idTree),
            typeTree;
        decorate(idTree,idDecl);
        typeTree = decoration(idDecl.getKid(2));
        
        // now check that the types of the expr and id are the same
        // visit the expr tree and get back its type
        if ( (t.getKid(2).accept(this)) != typeTree) {
            constraintError(ConstrainerErrors.BadAssignmentType);
        }
        return null;
    }
        
    public Object visitIntTree(AST t) {
        decorate(t,intTree);
        return intTree;
    }
        
    public Object visitIdTree(AST t) {
        AST decl = lookup(t);
        decorate(t,decl);
        return decoration(decl.getKid(2));
    }
        
    public Object visitRelOpTree(AST t) {
        AST leftOp = t.getKid(1),
            rightOp = t.getKid(2);
        if ( (AST)(leftOp.accept(this)) != (AST)(rightOp.accept(this)) ) {
            constraintError(ConstrainerErrors.TypeMismatchInExpr);
        }
        decorate(t,boolTree);
        return boolTree;
    }
 
/**
 *  Constrain the expression tree with an adding op at the root:<br>
 *  e.g. t1 + t2<br>
 *  check that the types of t1 and t2 match, if it's a plus tree
 *  then the types must be a reference to the intTree
 *  @return the type of the tree
*/
    public Object visitAddOpTree(AST t) {
        AST leftOpType = (AST)(t.getKid(1).accept(this)),
            rightOpType = (AST)(t.getKid(2).accept(this));
        if (leftOpType != rightOpType) {
            constraintError(ConstrainerErrors.TypeMismatchInExpr);
        }
        decorate(t,leftOpType);
        return leftOpType;
    }
        
    public Object visitMultOpTree(AST t) {
        return visitAddOpTree(t);
    }

    public Object visitIntTypeTree(AST t) {return null;}
    public Object visitBoolTypeTree(AST t) {return null;}
    public Object visitFormalsTree(AST t) {return null;}
    public Object visitActualArgsTree(AST t) {return null;}
    
    void constraintError(ConstrainerErrors err) {
        PrintVisitor v1 = new PrintVisitor();
        v1.visitProgramTree(t);
        System.out.println("****CONSTRAINER ERROR: " + err + "   ****");
        System.exit(1);
        return;
    }
    
}