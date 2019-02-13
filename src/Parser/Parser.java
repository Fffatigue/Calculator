package Parser;

import Lexer.*;

import java.io.IOException;
import java.io.Reader;

public class Parser {
    private Lexer lexer;
    private Lexeme currentLexeme;

    public Parser(Reader reader) throws IOException, LexerException {
        lexer = new Lexer( reader );
        currentLexeme = lexer.getLexeme();
    }

    public int calculate() throws ParserException, IOException, LexerException {
        int temp = parseExpr();
        if(currentLexeme.getLexemeType() == LexemeType.EOF){
            return temp;
        }
        throw new ParserException();
    }

    int parseExpr() throws IOException, LexerException, ParserException {
        int temp = parseTerm();
        while (currentLexeme.getLexemeType() == LexemeType.PLUS || currentLexeme.getLexemeType() == LexemeType.MINUS) {
            if (currentLexeme.getLexemeType() == LexemeType.PLUS) {
                currentLexeme = lexer.getLexeme();
                temp += parseTerm();
            } else {
                currentLexeme = lexer.getLexeme();
                temp -= parseTerm();
            }
        }
        return temp;
    }

    int parseTerm() throws IOException, LexerException, ParserException {
        int temp = parseFactor();
        while (currentLexeme.getLexemeType() == LexemeType.MULT || currentLexeme.getLexemeType() == LexemeType.DIV) {
            if (currentLexeme.getLexemeType() == LexemeType.MULT) {
                currentLexeme = lexer.getLexeme();
                temp *= parseTerm();
            } else {
                currentLexeme = lexer.getLexeme();
                temp /= parseTerm();
            }
        }
        return temp;
    }

    int parseFactor() throws IOException, LexerException, ParserException {
        int temp = parsePower();
        if(currentLexeme.getLexemeType() == LexemeType.POW){
            currentLexeme = lexer.getLexeme();
            temp = (int)Math.pow(temp,parseFactor());
        }
        return temp;
    }

    int parsePower() throws IOException, LexerException, ParserException {
        if(currentLexeme.getLexemeType() == LexemeType.MINUS){
            currentLexeme = lexer.getLexeme();
            return -parseAtom();
        }
        return parseAtom();
    }

    int parseAtom() throws IOException, LexerException, ParserException {
        if(currentLexeme.getLexemeType() == LexemeType.NUM){
            int m = Integer.parseInt( currentLexeme.getText() );
            currentLexeme = lexer.getLexeme();
            return m;
        }
        if(currentLexeme.getLexemeType() == LexemeType.OPEN_BRACKET){
            currentLexeme = lexer.getLexeme();
            int temp = parseExpr();
            if(currentLexeme.getLexemeType() != LexemeType.CLOSE_BRACKET){
                throw new ParserException();
            }
            currentLexeme = lexer.getLexeme();
            return temp;
        }
        throw new ParserException();
    }
}
