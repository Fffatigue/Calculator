package Lexer;

public class Lexeme {
    private String text;
    private LexemeType lexemeType;

    public Lexeme(LexemeType lexemeType, String text) {
        this.lexemeType = lexemeType;
        this.text = text;
    }

    public LexemeType getLexemeType() {
        return lexemeType;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (o == this) return true;
        if (!(o instanceof Lexeme)) return false;
        Lexeme other = (Lexeme) o;
        return other.getLexemeType() == lexemeType && other.getText().equals( text);
    }
}
