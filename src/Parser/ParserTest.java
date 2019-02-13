package Parser;

import Lexer.Lexer;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringReader;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void calculate() {
        try {
            Parser parser = new Parser( new StringReader( "5+3*(2-5)^3" ) );
            assertEquals( -76, parser.calculate() );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void parseExpr() {
        try {
            Parser parser = new Parser( new StringReader( "5+2-4" ) );
            assertEquals( 3, parser.parseExpr() );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void parseTerm() {
        try {
            Parser parser = new Parser( new StringReader( "5*4/2" ) );
            assertEquals( 10, parser.parseTerm() );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void parseFactor() {
        try {
            Parser parser = new Parser( new StringReader( "5^2" ) );
            assertEquals( 25, parser.parseFactor() );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void parsePower() {
        try {
            Parser parser = new Parser( new StringReader( "-10" ) );
            assertEquals( -10, parser.parsePower() );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void parseAtom() {
        try {
            Parser parser = new Parser( new StringReader( "6" ) );
            assertEquals( 6, parser.parseAtom() );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}