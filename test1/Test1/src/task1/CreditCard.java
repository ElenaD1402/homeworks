package task1;

public class CreditCard extends Card {
    public CreditCard(String cardHolder, String pin, int balance) {
        super("credit", cardHolder, pin, balance, false);
    }

    @Override
    public void withdraw(double withdrawAmount) {
        if (!isBlocked) {
            this.balance -= withdrawAmount;
            System.out.println("Please collect your money");
        } else {
            System.out.println("Your card is blocked");
        }
    }
}