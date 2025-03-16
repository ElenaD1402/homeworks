package task1.exceptions;

import java.util.InputMismatchException;

public class InputNameMismatchException extends InputMismatchException {
    public InputNameMismatchException() {
        super("Cardholder name is invalid.");
    }
}
