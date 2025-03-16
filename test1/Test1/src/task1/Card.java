package task1;

import task1.exceptions.*;

public abstract class Card {
    protected String type;
    protected String cardHolder;
    protected String pin;
    protected double balance;
    protected boolean isBlocked;

    public Card() {
        this("debit", "Card Holder", "1234", 0.0, false);
    }

    public Card(String type, String cardHolder, String pin, double balance, boolean isBlocked) {
        if (type.equals("debit") || type.equals("credit")) {
            this.type = type;
        } else throw new InputTypeMismatchException();
        if (cardHolder.matches("[A-Za-z]{0,15}\\s[A-Za-z]{0,15}")) {
            this.cardHolder = cardHolder;
        } else throw new InputNameMismatchException();
        if (pin.matches("[0-9]{4}")) {
            this.pin = pin;
        } else throw new InputCodeMismatchException();
        if (type.equals("debit")) {
            if (balance >= 0) {
                this.balance = balance;
            } else throw new InputBalanceMismatchException();
        } else {
            this.balance = balance;
        }
        this.isBlocked = isBlocked;
    }

    public String getType() {
        return type;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public double deposit(double depositAmount) throws BlockedException {
        if (isBlocked) {
            throw new BlockedException();
        }
        return this.balance += depositAmount;
    }

    public abstract double withdraw(double withdrawAmount) throws BlockedException, NegativeBalanceException;

    public double exchange(double exchangeRate, String currency) throws BlockedException {
        if (isBlocked) {
            throw new BlockedException();
        }
        return this.getBalance() / exchangeRate;
    }
}
