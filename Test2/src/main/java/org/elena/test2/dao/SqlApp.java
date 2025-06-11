package org.elena.test2.dao;

import org.elena.test2.dto.AccountDto;
import org.elena.test2.dto.TransactionDto;
import org.elena.test2.dto.UserDto;

public class SqlApp {

    public void createUser(UserDto userDto) {
        if (userDto.getAddress() == null) {
            UserDao.createUserWithoutAddress(userDto);
        } else {
            UserDao.createUserWithAddress(userDto);
        }
    }

    public void createAccount(AccountDto accountDto) {
        if (AccountDao.isAccountCreated(accountDto.getName(), accountDto.getCurrency())) {
            throw new RuntimeException("Account for user " + accountDto.getName() + " with currency " + accountDto.getCurrency() + " already exists");
        } else if (accountDto.getBalance() < 0.000d || accountDto.getBalance() > 2_000_000_000.000d) {
            throw new RuntimeException("Balance cannot be less than 0 and greater than 2’000’000’000");
        } else {
            AccountDao.createAccount(accountDto);
        }
    }

    public void withdraw(TransactionDto transactionDto) {
        if (Math.abs(transactionDto.getAmount()) > 100_000_000.000d) {
            throw new RuntimeException("Transaction cannot be greater than 100’000’000");
        } else if (transactionDto.getAmount() < 0) {
            throw new RuntimeException("Transaction amount should be positive number");
        } else {
            double amount = -1 * transactionDto.getAmount();
            transactionDto.withdraw();
            double balance = AccountDao.getBalanceByNameAndCurrency(transactionDto.getName(), transactionDto.getCurrency());
            if (balance + amount < 0) {
                throw new RuntimeException("Insufficient funds! Your current balance is " + balance);
            } else {
                balance += amount;
                AccountDao.updateBalance(transactionDto.getName(), transactionDto.getCurrency(), balance);
                TransactionDao.createTransaction(transactionDto);
            }
        }
    }

    public void deposit(TransactionDto transactionDto) {
        if (Math.abs(transactionDto.getAmount()) > 100_000_000.000d) {
            throw new RuntimeException("Transaction cannot be greater than 100’000’000");
        } else if (transactionDto.getAmount() < 0) {
            throw new RuntimeException("Transaction amount should be positive number");
        } else {
            double balance = AccountDao.getBalanceByNameAndCurrency(transactionDto.getName(), transactionDto.getCurrency());
            if (balance + transactionDto.getAmount() > 2_000_000_000.000d) {
                throw new RuntimeException(String.format("Balance cannot be greater than 2’000’000’000. Your current balance is %10.3f", balance));
            } else {
                balance += transactionDto.getAmount();
                AccountDao.updateBalance(transactionDto.getName(), transactionDto.getCurrency(), balance);
                TransactionDao.createTransaction(transactionDto);
            }
        }
    }
}
