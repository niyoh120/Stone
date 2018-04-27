package stone.token;

public class NumToken extends Token{
    private int value;

    public NumToken(int line, int v){
        super(line);
        value = v;
    }
    @Override
    public boolean isNumber() {
        return true;
    }

    @Override
    public String getText() {
        return Integer.toString(value);
    }

    @Override
    public int getNumber() {
        return value;
    }
}
