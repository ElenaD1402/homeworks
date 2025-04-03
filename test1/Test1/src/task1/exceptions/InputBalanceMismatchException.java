package task1.exceptions;

import java.util.InputMismatchException;

public class InputBalanceMismatchException extends InputMismatchException {
    public InputBalanceMismatchException() {
        super("Balance cannot be negative for debit card.");
    }
}
