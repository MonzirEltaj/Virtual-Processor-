import java.util.LinkedList;
import java.util.Optional;

public class TokenManager {
    private static LinkedList<Token> tokens;

    /**
     * Makes Token Manger
     * @param tokens
     */
    public TokenManager(LinkedList<Token> tokens){
        this.tokens=tokens;
        tokens.add(new Token(Token.TokenType.MULTIPLY));
        tokens.add(new Token(Token.TokenType.SHIFT));
        tokens.add(new Token(Token.TokenType.SUBTRACT));


    }

    /**
     * Peeks Token at index
     * @param j index we are peeking
     * @return Token if it exists, empty if it doesnt
     */
    public Optional<Token> Peek(int j){
        if(j<tokens.size()){
            return Optional.of((tokens.get(j)));
        }
        else{
            return Optional.empty();
        }
    }

    /**
     * @return true if there are more tokens, false if there is not
     */
    public static boolean MoreTokens(){
        if(tokens.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * If Token type is the same it removes it.
     * @param type
     * @return optional of Token if it was matched and removed, if not return empty
     */
    public static Optional<Token> MatchAndRemove(Token.TokenType type) {
        if (MoreTokens()) {
            Token token = (Token) tokens.getFirst();
            if (token.tokenType == type) {
                tokens.removeFirst();
                return Optional.of(token);
            }
        }
        return Optional.empty();
    }

}