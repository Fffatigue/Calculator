package Lexer;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private int currentSymbol;
    private Reader reader;

    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        currentSymbol = reader.read();
    }

    public Lexeme getLexeme() throws IOException, LexerException {
        while (Character.isSpaceChar( currentSymbol )) {
            currentSymbol = reader.read();
        }
        switch (currentSymbol) {
            case '+':
                currentSymbol = reader.read();
                return new Lexeme( LexemeType.PLUS, "+" );
            case '-':
                currentSymbol = reader.read();
                return new Lexeme( LexemeType.MINUS, "-" );
            case '/':
                currentSymbol = reader.read();
                return new Lexeme( LexemeType.DIV, "/" );
            case '*':
                currentSymbol = reader.read();
                return new Lexeme( LexemeType.MULT, "*" );
            case '(':
                currentSymbol = reader.read();
                return new Lexeme( LexemeType.OPEN_BRACKET, "(" );
            case ')':
                currentSymbol = reader.read();
                return new Lexeme( LexemeType.CLOSE_BRACKET, ")" );
            case '^':
                currentSymbol = reader.read();
                return new Lexeme( LexemeType.POW, "^" );
            case -1:
                currentSymbol = reader.read();
                return new Lexeme( LexemeType.EOF, "EOF" );
            default:
                if (Character.isDigit( currentSymbol )) {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (Character.isDigit( currentSymbol )) {
                        stringBuilder.append( (char) currentSymbol );
                        currentSymbol = reader.read();
                    }
                    return new Lexeme( LexemeType.NUM, stringBuilder.toString() );
                } else {
                    throw new LexerException();
                }
        }
    }
}
