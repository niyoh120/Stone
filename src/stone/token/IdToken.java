package stone.token;

public class IdToken extends Token {
    private String text;

    public IdToken(int line, String id) {
        super(line);
        text = id;
    }

    @Override
    public boolean isIdentifier() {
        return true;
    }

    @Override
    public String getText() {
        return text;
    }
}
