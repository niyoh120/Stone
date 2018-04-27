package stone.exception;

import stone.token.Token;

import java.io.IOException;

public class ParserException extends IOException {
    public ParserException(Token t) {
        this("", t);
    }

    public ParserException(String msg, Token t) {

    }

    private static String location(Token t) {
        if (Token.EOF == t) {
            return "the last line";
        } else {
            return "\"" + t.getText() + "\" at line " + t.getLineNumber();
        }

    }

    public ParserException(IOException e) {
        super(e);
    }

    public ParserException(String msg) {
        super(msg);
    }
}
