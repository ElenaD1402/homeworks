package org.elena.test2.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransactionDto {
    String name;
    double amount;
    String currency;
    String timestamp;

    public TransactionDto(String name, double amount, String currency) {
        this.name = name;
        this.amount = amount;
        this.currency = currency;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        this.timestamp = formatter.format(new Date());
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void withdraw() {
        this.amount *= -1;
    }

    public void print() {
        System.out.printf("%-20s %-20.3f %-10s %n", name, amount, currency);
    }
}
