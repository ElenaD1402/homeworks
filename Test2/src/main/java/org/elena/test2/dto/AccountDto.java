package org.elena.test2.dto;

public class AccountDto {
    String name;
    double balance;
    String currency;

    public AccountDto(String name, double balance, String currency) {
        this.name = name;
        this.balance = balance;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void print() {
        System.out.printf("%-20s %-20.3f %-10s %n", name, balance, currency);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof AccountDto) {
            AccountDto accountDto = (AccountDto) object;
            if (accountDto.getName().equals(this.getName())) {
                if (accountDto.getBalance() == this.getBalance()) {
                    if (accountDto.getCurrency().equals(this.getCurrency())) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
