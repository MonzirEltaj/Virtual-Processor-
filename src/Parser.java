import java.util.LinkedList;
import java.util.Optional;

public class Parser {
    //  MATH, ADD, SUBTRACT, MULTIPLY, AND, OR, NOT, XOR, COPY, HALT, BRANCH, JUMP, CALL, PUSH, LOAD, RETURN,
    //  NEWLINE,STORE, PEEK, POP, INTERRUPT, EQUAL, UNEQUAL, GREATER, LESS, GREATEROREQUAL, LESSOREQUAL, SHIFT, LEFT, RIGHT

    private LinkedList<Token> tokens;
    int currentTokenIndex;

    public Parser(LinkedList<Token> tokens) {
        this.tokens = tokens;
        this.currentTokenIndex = 0;
    }

    public void parse() throws Exception {
        while (currentTokenIndex < tokens.size()) {
            Token currentToken = tokens.get(currentTokenIndex);
            switch (currentToken.getType()) {
                case MATH:
                    parseMath();
                    break;
                case ADD:
                    parseMath();
                    break;
                case SUBTRACT:
                    parseMath();
                    break;
                case MULTIPLY:
                    parseMath();
                    break;
                case AND:
                    parseOP();
                    break;
                case OR:
                    parseOP();
                    break;
                case NOT:
                    parseOP();
                    break;
                case XOR:
                    parseOP();
                    break;
                case COPY:
                    parseRegisters();
                    break;
                case HALT:
                    parseRegisters();
                    break;
                case BRANCH:
                    parseRegisters();

                    break;
                case JUMP:
                    parseRegisters();
                    break;
                case CALL:
                    parseRegisters();
                    break;
                case PUSH:
                    parseRegisters();

                    break;
                case LOAD:
                    parseRegisters();
                    break;
                case RETURN:
                    parseRegisters();
                    break;
                case NEWLINE:
                    AcceptSeparators();
                    break;
                case STORE:
                    parseRegisters();
                    break;
                case PEEK:
                    parseRegisters();
                    break;
                case POP:
                    parseRegisters();
                    break;
                case INTERRUPT:
                    parseRegisters();
                    break;
                case EQUAL:
                    parseOp();
                    break;
                case UNEQUAL:
                    parseOp();
                    break;
                case GREATER:
                    parseOp();
                    break;
                case LESS:
                    parseOp();
                    break;
                case GREATEROREQUAL:
                    parseOp();
                    break;
                case LESSOREQUAL:
                    parseOp();
                    break;
                case SHIFT:
                    parseMovement();
                    break;
                case LEFT:
                    parseMovement();
                    break;
                case RIGHT:
                    parseMovement();
                    break;
                default:
                    throw new Exception("Unexpected token: " + currentToken.getType());
            }
        }
    }


    public void parseMath() {
        Token currentToken = tokens.get(currentTokenIndex);
        String tokenValue = currentToken.toString();
        int intValue = Integer.parseInt(tokenValue, 2);
        int last4Bits = intValue & 0b1111;
        String operation;
        switch (last4Bits) {
            case 0001:
                operation = "add";
                break;
            case 1100:
                operation = "subtract";
                break;
            case 0110:
                operation = "multiply";
                break;
            case 1111:
                operation = "divide";
                break;
            default:
                // Handle invalid operation
                throw new IllegalArgumentException("Invalid operation in 'math' token: " + tokenValue);
        }

        // Remove 'math' token
        TokenManager.MatchAndRemove(Token.TokenType.MATH);

        // Remove next token
        TokenManager.MatchAndRemove(Token.TokenType.ADD);
        // Or alternatively for brevity, you can just match and remove the same token type:
        // TokenManager.matchAndRemove(currentToken.getType(), tokens);

        // Update the current token index
        currentTokenIndex++;

        // You can now use the 'operation' string for further processing
    }





    public void parseOP() {
        // Get the current token
        Token currentToken = tokens.get(currentTokenIndex);

        // Determine the operation based on the token type
        String operation;
        switch (currentToken.getType()) {
            case AND:
                operation = "AND";
                break;
            case OR:
                operation = "OR";
                break;
            case NOT:
                operation = "NOT";
                break;
            case XOR:
                operation = "XOR";
                break;
            default:
                // Handle invalid token type
                throw new IllegalArgumentException("Invalid operation token: " + currentToken.getType());
        }

        // Remove the operation token
        TokenManager.MatchAndRemove(currentToken.getType());

        // Update the current token index
        currentTokenIndex++;


        // Remove the operation token
        tokens.remove(currentTokenIndex);

        // Update the current token index
        currentTokenIndex++;
    }



    public void parseRegisters() {
        Token currentToken = tokens.get(currentTokenIndex);
        TokenManager.MatchAndRemove(currentToken.getType());
        currentTokenIndex++;
    }


    /**
     * Accepts multiple separators and matches and removes them
     *
     * @return true if a separator was found
     */
    public boolean AcceptSeparators() {
        boolean isSeparator = false;

        while (TokenManager.MoreTokens()) {
            Optional<Token> nextToken = TokenManager.MatchAndRemove(Token.TokenType.NEWLINE);
            if (((Optional<?>) nextToken).isPresent()) {
                isSeparator = true;
            } else {
                break;
            }
        }

        return isSeparator;
    }


    public void parseOp() {
        Token currentToken = tokens.get(currentTokenIndex);
        String operation;
        switch (currentToken.getType()) {
            case EQUAL:
            case UNEQUAL:
            case GREATER:
            case LESS:
            case GREATEROREQUAL:
            case LESSOREQUAL:
                operation = currentToken.getType().toString();
                break;
            default:
                // Handle invalid token type
                throw new IllegalArgumentException("Invalid comparison operator token: " + currentToken.getType());
        }

        // Remove the operation token
        TokenManager.MatchAndRemove(currentToken.getType());

        // Update the current token index
        currentTokenIndex++;
    }

    public void parseMovement() {
        // Get the current token
        Token currentToken = tokens.get(currentTokenIndex);

        // Determine the movement based on the token type
        String movement;
        switch (currentToken.getType()) {
            case SHIFT:
            case LEFT:
            case RIGHT:
                movement = currentToken.getType().toString(); // Assuming you want to use the token type directly
                break;
            default:
                // Handle invalid token type
                throw new IllegalArgumentException("Invalid movement token: " + currentToken.getType());
        }

        // Remove the movement token
        TokenManager.MatchAndRemove(currentToken.getType());

        // Update the current token index
        currentTokenIndex++;
    }
public boolean Parse(){
        for(int x=0; x<8; x++){
          int result=2*5-7+1;
        }
        return true;
}

}
