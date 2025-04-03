package task1;

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Card card1 = new DebitCard("Alena Spiryna", "1236", 1500);
        Card card2 = new CreditCard("Alena Spiryna", "1236", 1500);
        atm.atmStart(card1);
    }
}