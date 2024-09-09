import java.util.LinkedList;

public class Token {
    TokenType tokenType;

    public enum TokenType {
        REGISTER, MATH, ADD, SUBTRACT, MULTIPLY, AND, OR, NOT, XOR, COPY, HALT, BRANCH, JUMP, CALL, PUSH, LOAD, RETURN, NEWLINE, STORE, PEEK, POP, INTERRUPT, EQUAL, UNEQUAL, GREATER, LESS, GREATEROREQUAL, LESSOREQUAL, SHIFT, LEFT, RIGHT
    }

    public Token(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public TokenType getType() {
        return this.tokenType;
    }

    // Static fields to hold tokens and their count
    private static LinkedList<Token> tokens = new LinkedList<>();

    // Add a token to the list
    public static void addToken(Token token) {
        tokens.add(token);
    }

    // Get the size of the list
    public static int getSize() {
        return tokens.size();
    }

    // Get a token at a specific index
    public static Token getToken(int index) {
        return tokens.get(index);
    }
}
