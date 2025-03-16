package task1.exceptions;

import java.util.InputMismatchException;

public class InputTypeMismatchException extends InputMismatchException {
    public InputTypeMismatchException() {
        super("Card type is invalid.");
    }
}
