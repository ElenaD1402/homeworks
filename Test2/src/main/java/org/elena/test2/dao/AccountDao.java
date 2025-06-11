package org.elena.test2.dao;

import org.elena.test2.dto.AccountDto;
import org.elena.test2.utils.ConnectionStringUtil;

import java.sql.*;

public class AccountDao {
    public static final String ADD_ACCOUNT_QUERY = "INSERT INTO Accounts (account_id, user_id, balance, currency) VALUES (?, ?, ?, ?)";
    public static final String UPDATE_BALANCE_QUERY = "UPDATE Accounts SET balance = ? WHERE account_id = (SELECT account_id from Accounts JOIN Users ON Accounts.user_id = Users.user_id WHERE name = '%s' AND currency = '%s')";

    public static boolean isAccountCreated(String name, String currency) {
        try (Statement statement = DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).createStatement()) {
            ResultSet resultSet = statement.executeQuery(String
                    .format("SELECT Accounts.user_id, account_id, currency FROM Accounts JOIN Users ON Users.user_id = Accounts.user_id WHERE name = '%s' AND currency = '%s'", name, currency));
            return resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException("Database is not connected");
        }
    }

    public static void createAccount(AccountDto accountDto) {
        try (PreparedStatement preparedStatement =
                     DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).prepareStatement(ADD_ACCOUNT_QUERY)) {
            preparedStatement.setInt(1, getNextAccountId());
            preparedStatement.setInt(2, getUserIdByName(accountDto.getName()));
            preparedStatement.setDouble(3, accountDto.getBalance());
            preparedStatement.setString(4, accountDto.getCurrency());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database is not connected");
        }
    }

    public static Integer getNextAccountId() {
        try (Statement statement = DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(account_id) as max FROM Accounts");
            if (resultSet.next()) {
                return resultSet.getInt("max") + 1;
            } else {
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database is not connected");
        }
    }

    public static Integer getUserIdByName(String name) {
        try (Statement statement = DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT user_id FROM Users WHERE name = '%s'", name));
            if (resultSet.next()) {
                return resultSet.getInt("user_id");
            } else {
                throw new RuntimeException("User " + name + " does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database is not connected");
        }
    }

    public static double getBalanceByNameAndCurrency(String name, String currency) {
        try (Statement statement = DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT balance from Accounts JOIN Users ON Accounts.user_id = Users.user_id WHERE name = '%s' AND currency = '%s'", name, currency));
            if (resultSet.next()) {
                return resultSet.getDouble("balance");
            } else {
                throw new RuntimeException("Account for " + name + " with currency " + currency + " does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database is not connected");
        }
    }

    public static void updateBalance(String name, String currency, double balance) {
        try (PreparedStatement preparedStatement =
                     DriverManager.getConnection(ConnectionStringUtil.getConnectionString())
                             .prepareStatement(String.format(UPDATE_BALANCE_QUERY, name, currency))) {
            preparedStatement.setDouble(1, balance);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database is not connected");
        }
    }
}
