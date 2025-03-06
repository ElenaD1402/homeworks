package task1;

import java.util.InputMismatchException;

public abstract class Card {
    protected String type;
    protected String cardHolder;
    protected String pin;
    protected double balance;
    protected boolean isBlocked;

    public Card(String type, String cardHolder, String pin, double balance, boolean isBlocked) {
        if (type.equals("debit") || type.equals("credit")) {
            this.type = type;
        } else throw new InputMismatchException("Card type is invalid.");
        if (cardHolder.matches("[A-Za-z]{0,15}\\s[A-Za-z]{0,15}")) {
            this.cardHolder = cardHolder;
        } else throw new InputMismatchException("Cardholder name is invalid.");
        if (pin.matches("[0-9]{4}")) {
            this.pin = pin;
        } else throw new InputMismatchException("PIN code is invalid.");
        if (type.equals("debit")) {
            if (balance >= 0) {
                this.balance = balance;
            } else throw new InputMismatchException("Balance cannot be negative for debit card.");
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

    public void checkBalance() {
        if (!isBlocked) {
            System.out.println("Your balance is " + this.getBalance() + " BYN");
        } else {
            System.out.println("Your card is blocked");
        }
    }

    public void deposit(double depositAmount) {
        if (!isBlocked) {
            this.balance += depositAmount;
            System.out.println("Your money has been successfully deposited");
        } else {
            System.out.println("Your card is blocked");
        }
    }

    public abstract void withdraw(double withdrawAmount);

    public void exchange(double exchangeRate, String currency) {
        if (!isBlocked) {
            System.out.println("Your balance is " + this.getBalance() / exchangeRate + " " + currency);
        } else {
            System.out.println("Your card is blocked");
        }
    }
}
