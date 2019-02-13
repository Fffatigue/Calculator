package Lexer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class LexerTest {

    @Test
    void getLexeme_plus() {
        try {
            Lexer lexer = new Lexer( new StringReader( "+" ) );
            Lexeme a = lexer.getLexeme();
            assertEquals( a, new Lexeme( LexemeType.PLUS, "+" ) );

        } catch (IOException | LexerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getLexeme_number() {
        try {
            Lexer lexer = new Lexer( new StringReader( "55" ) );
            Lexeme a = lexer.getLexeme();
            assertEquals( a, new Lexeme( LexemeType.NUM, "55" ) );

        } catch (IOException | LexerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getLexeme_eof() {
        try {
            Lexer lexer = new Lexer( new StringReader( "" ) );
            Lexeme a = lexer.getLexeme();
            assertEquals( a, new Lexeme( LexemeType.EOF, "EOF" ) );

        } catch (IOException | LexerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getLexeme_spaces() {
        try {
            Lexer lexer = new Lexer( new StringReader( "    5" ) );
            Lexeme a = lexer.getLexeme();
            assertEquals( a, new Lexeme( LexemeType.NUM, "5" ) );

        } catch (IOException | LexerException e) {
            e.printStackTrace();
        }
    }

    @Test
    void getLexeme_exception() {
        try {
            Lexer lexer = new Lexer( new StringReader( "f" ) );
            Assertions.assertThrows(LexerException.class, lexer::getLexeme );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
