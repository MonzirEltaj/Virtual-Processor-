import java.util.*;

public class Lexer {
    private StringHandler stringHandler;
    private int lineNum;
    private int charPos;
    private HashMap<String, Token.TokenType> keywords = new HashMap<>();
    private LinkedList<Token> tokens = new LinkedList<Token>();


    // Constructor initializes lexer with input string
    public Lexer(String input) {

        this.stringHandler = new StringHandler(input);
        this.lineNum = 1;
        this.charPos = 1;
        tokens.add(new Token(Token.TokenType.OR));
        tokens.add(new Token(Token.TokenType.ADD));
        tokens.add(new Token(Token.TokenType.SUBTRACT));
        tokens.add(new Token(Token.TokenType.MULTIPLY));
        tokens.add(new Token(Token.TokenType.AND));
        tokens.add(new Token(Token.TokenType.OR));
        tokens.add(new Token(Token.TokenType.NOT));
        tokens.add(new Token(Token.TokenType.XOR));
        tokens.add(new Token(Token.TokenType.COPY));
        tokens.add(new Token(Token.TokenType.HALT));
        tokens.add(new Token(Token.TokenType.BRANCH));
        tokens.add(new Token(Token.TokenType.JUMP));
        tokens.add(new Token(Token.TokenType.CALL));
        tokens.add(new Token(Token.TokenType.PUSH));
        tokens.add(new Token(Token.TokenType.LOAD));
        tokens.add(new Token(Token.TokenType.RETURN));
        tokens.add(new Token(Token.TokenType.NEWLINE));
        tokens.add(new Token(Token.TokenType.STORE));
        tokens.add(new Token(Token.TokenType.PEEK));
        tokens.add(new Token(Token.TokenType.POP));
        tokens.add(new Token(Token.TokenType.INTERRUPT));
        tokens.add(new Token(Token.TokenType.EQUAL));
        tokens.add(new Token(Token.TokenType.UNEQUAL));
        tokens.add(new Token(Token.TokenType.GREATER));
        tokens.add(new Token(Token.TokenType.LESS));
        tokens.add(new Token(Token.TokenType.GREATEROREQUAL));
        tokens.add(new Token(Token.TokenType.LESSOREQUAL));
        tokens.add(new Token(Token.TokenType.SHIFT));
        tokens.add(new Token(Token.TokenType.LEFT));
        tokens.add(new Token(Token.TokenType.RIGHT));

        setKeywords(keywords);
    }

    // Method to set up keywords
    private void setKeywords(HashMap<String, Token.TokenType> keywords) {
        keywords.put("math", Token.TokenType.MATH);
        keywords.put("add", Token.TokenType.ADD);
        keywords.put("subtract", Token.TokenType.SUBTRACT);
        keywords.put("multiply", Token.TokenType.MULTIPLY);
        keywords.put("and", Token.TokenType.AND);
        keywords.put("or", Token.TokenType.OR);
        keywords.put("not", Token.TokenType.NOT);
        keywords.put("xor", Token.TokenType.XOR);
        keywords.put("copy", Token.TokenType.COPY);
        keywords.put("halt", Token.TokenType.HALT);
        keywords.put("branch", Token.TokenType.BRANCH);
        keywords.put("jump", Token.TokenType.JUMP);
        keywords.put("call", Token.TokenType.CALL);
        keywords.put("push", Token.TokenType.PUSH);
        keywords.put("load", Token.TokenType.LOAD);
        keywords.put("return", Token.TokenType.RETURN);
        keywords.put("store", Token.TokenType.STORE);
        keywords.put("peek", Token.TokenType.PEEK);
        keywords.put("pop", Token.TokenType.POP);
        keywords.put("interrupt", Token.TokenType.INTERRUPT);
        keywords.put("equal", Token.TokenType.EQUAL);
        keywords.put("unequal", Token.TokenType.UNEQUAL);
        keywords.put("greater", Token.TokenType.GREATER);
        keywords.put("less", Token.TokenType.LESS);
        keywords.put("greaterOrEqual", Token.TokenType.GREATEROREQUAL);
        keywords.put("lessOrEqual", Token.TokenType.LESSOREQUAL);
        keywords.put("shift", Token.TokenType.SHIFT);
        keywords.put("left", Token.TokenType.LEFT);

    }

    public LinkedList<Token> lex() throws Exception {
        while (!stringHandler.isDone()) {
            char currentChar = stringHandler.getChar();
            if (currentChar == '\n') {
                tokens.add(new Token(Token.TokenType.NEWLINE));
                lineNum++;
                charPos = 1;
                continue;
            }
            if (Character.isLetter(currentChar) ) {
                String word = extractWord(stringHandler);
                if (keywords.containsKey(word)) {
                    tokens.add(new Token(keywords.get(word)));
                }
            }

            charPos++;
        }
        return tokens;
    }

    private String extractWord(StringHandler handler) {
        StringBuilder wordBuilder = new StringBuilder();
        while (!handler.isDone()) {
            char currentChar = handler.Peek(0);
            if (Character.isLetterOrDigit(currentChar) ) {
                wordBuilder.append(handler.getChar());
            } else {
                break;
            }
        }
        return wordBuilder.toString();
    }
}
