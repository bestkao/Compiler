package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class FloatTree extends AST {
    private Symbol symbol;

/**
 *  @param tok is the Token containing the String representation of the float
 *  literal; we keep the String rather than converting to an float value
 *  so we don't introduce any machine dependencies with respect to float
 *  representations
*/
    public FloatTree(Token tok) {
        this.symbol = tok.getSymbol();
    }

    public Object accept(ASTVisitor v) {
        return v.visitFloatTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }

}

