package stone.test;

import stone.exception.ParserException;
import stone.lexer.Lexer;
import stone.token.Token;
import stone.utils.CodeDiaLog;

public class LexerRunner {
    public static void main(String[] args) throws ParserException{
        Lexer l = new Lexer(new CodeDiaLog());
        for(Token t;(t=l.getNextToken())!= Token.EOF;){
            System.out.println("=> " +t.getText());
        }

    }
}
