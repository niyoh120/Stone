package stone.token;

public class StringToken extends Token{
    private String literal;
    public StringToken(int line, String str){
        super(line);
        literal = str;
    }

    @Override
    public boolean isString() {
        return true;
    }

    @Override
    public String getText() {
        return literal;
    }
}
