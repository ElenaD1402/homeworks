package task1.exceptions;

import java.util.InputMismatchException;

public class InputCodeMismatchException extends InputMismatchException {
    public InputCodeMismatchException() {
        super("PIN code is invalid.");
    }
}
