package org.elena.test2;

import org.elena.test2.dto.AccountDto;
import org.elena.test2.dto.TransactionDto;
import org.elena.test2.dto.UserDto;
import org.elena.test2.utils.ConnectionStringUtil;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    public static final String GET_USERS_QUERY = "SELECT name, address FROM Users";
    public static final String GET_ACCOUNTS_QUERY = "SELECT name, balance, currency FROM Accounts JOIN Users ON Users.user_id = Accounts.user_id";
    public static final String GET_TRANSACTIONS_QUERY = "SELECT name, amount, currency FROM Transactions JOIN Accounts ON Accounts.account_id = Transactions.account_id JOIN Users ON Users.user_id = Accounts.user_id";

    public static List<UserDto> getUsers() {
        try (Statement statement = DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).createStatement()) {
            ResultSet usersResultSet = statement.executeQuery(GET_USERS_QUERY);
            List<UserDto> usersDtoList = new ArrayList<>();
            while (usersResultSet.next()) {
                usersDtoList.add(new UserDto(usersResultSet.getString(1),
                        usersResultSet.getString(2)));
            }
            return usersDtoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<AccountDto> getAccounts() {
        try (Statement statement = DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).createStatement()) {
            ResultSet accountsResultSet = statement.executeQuery(GET_ACCOUNTS_QUERY);
            List<AccountDto> accountsDtoList = new ArrayList<>();
            while (accountsResultSet.next()) {
                accountsDtoList.add(new AccountDto(accountsResultSet.getString(1),
                        accountsResultSet.getDouble(2),
                        accountsResultSet.getString(3)));
            }
            return accountsDtoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<TransactionDto> getTransactions() {
        try (Statement statement = DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).createStatement()) {
            ResultSet transactionsResultSet = statement.executeQuery(GET_TRANSACTIONS_QUERY);
            List<TransactionDto> transactionsDtoList = new ArrayList<>();
            while (transactionsResultSet.next()) {
                transactionsDtoList.add(new TransactionDto(transactionsResultSet.getString(1),
                        transactionsResultSet.getDouble(2),
                        transactionsResultSet.getString(3)));
            }
            return transactionsDtoList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
