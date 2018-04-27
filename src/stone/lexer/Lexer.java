package stone.lexer;

import stone.exception.ParserException;
import stone.token.IdToken;
import stone.token.NumToken;
import stone.token.StringToken;
import stone.token.Token;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    public static String numberRegexPat = "(?<number>[0-9]+)";
    public static String idRegexPat = "(?<id>[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})";
    public static String stringRegexPat = "(?<string>\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")";
    public static String commentRegexPat = "(?<comment>//.*)";
    public static String whiteRegexPat = "\\s*";
    public static String regexPat
            = whiteRegexPat
            + "(?<line>"
            + String.join("|", Arrays.asList(commentRegexPat, numberRegexPat, idRegexPat, stringRegexPat))
            + ")?";
    private Pattern pattern = Pattern.compile(regexPat);
    private ArrayList<Token> queue = new ArrayList<Token>();
    private boolean hasMore;
    private LineNumberReader reader;

    public Lexer(Reader r) {
        hasMore = true;
        reader = new LineNumberReader(r);
    }

    public Token getNextToken() throws ParserException {
        if (fillQueue(0)) {
            return queue.remove(0);
        } else {
            return Token.EOF;
        }
    }

    public Token peekNextToken(int i) throws ParserException {
        if (fillQueue(i)) {
            return queue.get(i);
        } else {
            return Token.EOF;
        }
    }

    private boolean fillQueue(int i) throws ParserException {
        while (i >= queue.size()) {
            if (hasMore) {
                readLine();
            } else {
                return false;
            }
        }
        return true;
    }

    protected void readLine() throws ParserException {
        String line;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new ParserException(e);
        }
        if (line == null) {
            hasMore = false;
            return;
        }
        int lineNo = reader.getLineNumber();
        Matcher matcher = pattern.matcher(line);
        matcher.useAnchoringBounds(false).useTransparentBounds(true);
        int pos = 0;
        int end = line.length();
        while (pos < end) {
            matcher.region(pos, end);
            if (matcher.lookingAt()) {
                addToken(lineNo, matcher);
                pos = matcher.end();
            } else {
                throw new ParserException("bad token at line " + lineNo);
            }
        }
        queue.add(new IdToken(lineNo, Token.EOL));
    }

    protected void addToken(int lineNo, Matcher matcher) {
        String m = matcher.group("line");
        if (m != null) {
            if (matcher.group("comment") == null) {
                Token token;
                if (matcher.group("number") != null) {
                    token = new NumToken(lineNo, Integer.parseInt(m));
                } else if (matcher.group("id") != null) {
                    token = new IdToken(lineNo, m);
                } else {
                    token = new StringToken(lineNo, m);
                }
                queue.add(token);
            }
        }
    }

    protected String toStringLiteral(String str) {
        StringBuilder sb = new StringBuilder();
        int len = str.length() - 1;
        for (int i = 1; i < len; i++) {
            char c = str.charAt(i);
            if ('\\' == c && i + 1 < len) {
                int c2 = str.charAt(i + 1);
                if ('"' == c2 || '\\' == c2) {
                    c = str.charAt(++i);
                } else if ('n' == c2) {
                    c = '\n';
                    i++;
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
