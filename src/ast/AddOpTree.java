package ast;

import lexer.Symbol;
import lexer.Token;
import visitor.*;

public class AddOpTree extends AST {
    private Symbol symbol;

    public AddOpTree(Token tok) {
        this.symbol = tok.getSymbol();
    }

    public Object accept(ASTVisitor v) {
        return v.visitAddOpTree(this);
    }

    public Symbol getSymbol() {
        return symbol;
    }

}
