package task1;

public class DebitCard extends Card {
    public DebitCard(String cardHolder, String pin, int balance) {
        super("debit", cardHolder, pin, balance, false);
    }

    @Override
    public void withdraw(double withdrawAmount) {
        if (!isBlocked) {
            if (this.balance >= withdrawAmount) {
                this.balance -= withdrawAmount;
                System.out.println("Please collect your money");
            } else {
                System.out.println("Sorry! Insufficient Funds");
            }
        } else {
            System.out.println("Your card is blocked");
        }
    }
}