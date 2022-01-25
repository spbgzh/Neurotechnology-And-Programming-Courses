package analyzer.lexemeAnalyzer;

import analyzer.Analyzer;
import data.Repository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static analyzer.lexemeAnalyzer.ExpressionChars.*;

/**
 * <h3>Parser rules for analyzer:</h3>
 * <p>expression: <b>plusMinus*</b> and <b>endOfField</b>;</p>
 * <p>plusMinus: <b>multiplyDivide</b> ((<b>'+' or '-'</b>) <b>multiplyDivide</b>)*;</p>
 * <p>multiplyDivide: <b>factor</b> ((<b>'*'</b> or <b>'/'</b>) <b>factor*</b>);</p>
 * <p>factor: <b>NUMBER</b> or <b>'('expression')'</b>.</p>
 */
public class AnalyzerImpl implements Analyzer {
    private final String UNEXPECTED_TOKEN = "Unexpected token: ";
    private final String AT_POSITION = " at position: ";
    private static final String UNEXPECTED_CHARACTER = "Unexpected character: ";

    /**
     * This is the method that will collect the analyzer.
     *
     * @return result expression
     */
    @Override
    public int getExpressionResult(String expression, HttpServletRequest req, Repository repository) {
        List<Lexeme> lexemes = lexemeAnalyze(expression, req, repository);
        return bracketsExpression(new LexemeBuffer(lexemes));
    }

    /**
     * This is the method that will parse each Lexeme and add her to list.
     *
     * @return ArrayList lexemes.
     * @throws RuntimeException if unexpected character
     */
    private List<Lexeme> lexemeAnalyze(String expression, HttpServletRequest req, Repository repository) {
        ArrayList<Lexeme> lexemes = new ArrayList<>();
        int position = 0;
        while (position < expression.length()) {
            char character = expression.charAt(position);
            switch (character) {
                case LEFT_BRACKET_CHAR : {
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, character));
                    position++;
                    break;
                }
                case RIGHT_BRACKET_CHAR : {
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, character));
                    position++;
                    break;
                }
                case PLUS_CHAR :{
                    lexemes.add(new Lexeme(LexemeType.OPERATOR_PLUS, character));
                    position++;
                    break;
                }
                case MINUS_CHAR : {
                    lexemes.add(new Lexeme(LexemeType.OPERATOR_MINUS, character));
                    position++;
                    break;
                }
                case MULTIPLY_CHAR :{
                    lexemes.add(new Lexeme(LexemeType.OPERATOR_MUL, character));
                    position++;
                    break;
                }
                case DIVIDE_CHAR : {
                    lexemes.add(new Lexeme(LexemeType.OPERATOR_DIV, character));
                    position++;
                    break;
                }
                default : {
                    if (isCertainCharacters(character, ZERO_CHAR, NINE_CHAR)) { //number parse
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(character);
                            position++;
                            if (position >= expression.length()) {
                                break;
                            }
                            character = expression.charAt(position);
                        } while (isCertainCharacters(character, ZERO_CHAR, NINE_CHAR));
                        lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                    } else if (isCertainCharacters(character, A_CHAR, Z_CHAR)) { //letter parse
                        StringBuilder sb = new StringBuilder();
                        do {
                            String paramValue = repository
                                    .getParametersData()
                                    .get(req.getSession().getId())
                                    .get(String.valueOf(character));
                            sb.append(paramValue);
                            position++;
                            if (position >= expression.length()) {
                                break;
                            }
                            character = expression.charAt(position);
                        } while (isCertainCharacters(character, A_CHAR, Z_CHAR));
                        lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                    } else {
                        if (character != SPACE_CHAR) {
                            throw new RuntimeException(UNEXPECTED_CHARACTER + character);
                        }
                        position++;
                    }
                }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, "")); //end of field add to list
        return lexemes;
    }

    private boolean isCertainCharacters(char character, char first, char last) {
        return (character <= last && character >= first);
    }

    /**
     * This is method for parsing expression and will get result.
     *
     * @param lexemes LexemeBuffer
     * @return result value for expression
     */
    private int bracketsExpression(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.type == LexemeType.EOF) {
            return 0;
        } else {
            lexemes.back();
            return plusMinus(lexemes);
        }
    }

    /**
     * This is method for parsing subexpression (plus, minus) with factor and get result.
     *
     * @param lexemes LexemeBuffer
     * @return result value with plus and minus
     * @throws RuntimeException if Unexpected token at position
     */
    private int plusMinus(LexemeBuffer lexemes) {
        int value = multiplyDivide(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OPERATOR_PLUS:
                    value += multiplyDivide(lexemes);
                    break;
                case OPERATOR_MINUS:
                    value -= multiplyDivide(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET: {
                    lexemes.back();
                    return value;
                }
                default:
                    throw new RuntimeException(UNEXPECTED_TOKEN + lexeme.value + AT_POSITION + lexemes.getPos());
            }
        }
    }

    /**
     * This is method for parse subexpression (multiply, divide) with factor and get result.
     *
     * @param lexemes LexemeBuffer
     * @return result value with multiply and divide
     * @throws RuntimeException if Unexpected token at position
     */
    private int multiplyDivide(LexemeBuffer lexemes) {
        int value = factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.type) {
                case OPERATOR_MUL:
                    value *= factor(lexemes);
                    break;
                case OPERATOR_DIV:
                    value /= factor(lexemes);
                    break;
                case EOF:
                case OPERATOR_PLUS:
                case RIGHT_BRACKET:
                case OPERATOR_MINUS: {
                    lexemes.back();
                    return value;
                }
                default:
                    throw new RuntimeException(UNEXPECTED_TOKEN + lexeme.value + AT_POSITION + lexemes.getPos());
            }
        }
    }

    /**
     * This is method for parse numbers and expressions in brackets and get result.
     *
     * @param lexemes LexemeBuffer
     * @return number value or result expression value in bracket
     * @throws RuntimeException if Unexpected token at position
     */
    private int factor(LexemeBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.type) {
            case OPERATOR_MINUS : {
                return -factor(lexemes);
            }
            case NUMBER : {
                return Integer.parseInt(lexeme.value);
            }
            case LEFT_BRACKET : {
                int value = bracketsExpression(lexemes);
                lexeme = lexemes.next();
                if (lexeme.type != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException(UNEXPECTED_TOKEN + lexeme.value + AT_POSITION + lexemes.getPos());
                }
                return value;
            }
            default : throw new RuntimeException(UNEXPECTED_TOKEN + lexeme.value + AT_POSITION + lexemes.getPos());
        }
    }
}