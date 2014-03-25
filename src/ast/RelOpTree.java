package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class RelOpTree extends AST {
    private Symbol symbol;

/**
 *  @param tok contains the Symbol which indicates the specific relational operator
*/
    public RelOpTree(Token tok) {
        this.symbol = tok.getSymbol();
    }

    public Object accept(ASTVisitor v) {
        return v.visitRelOpTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }

}

