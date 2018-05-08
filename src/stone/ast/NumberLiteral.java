package stone.ast;

import stone.token.Token;

public class NumberLiteral extends ASTLeaf {
    public NumberLiteral(Token t){
        super(t);
    }
    public Token value() {
        return token;
    }
}
