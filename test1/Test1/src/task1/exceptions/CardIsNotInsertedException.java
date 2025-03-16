package task1.exceptions;

public class CardIsNotInsertedException extends Exception {
    public CardIsNotInsertedException() {
        super("Card is not inserted.");
    }
}
