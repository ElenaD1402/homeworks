package org.elena.test2.dao;

import org.elena.test2.dto.TransactionDto;
import org.elena.test2.utils.ConnectionStringUtil;

import java.sql.*;

public class TransactionDao {
    public static final String ADD_TRANSACTION_QUERY = "INSERT INTO Transactions (transaction_id, account_id, amount, timestamp) VALUES (?, ?, ?, ?)";

    public static void createTransaction(TransactionDto transactionDto) {
        try (PreparedStatement preparedStatement =
                     DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).prepareStatement(ADD_TRANSACTION_QUERY)) {
            preparedStatement.setInt(1, getNextTransactionId());
            preparedStatement.setInt(2, getAccountIdByNameAndCurrency(transactionDto.getName(), transactionDto.getCurrency()));
            preparedStatement.setDouble(3, transactionDto.getAmount());
            preparedStatement.setString(4, transactionDto.getTimestamp());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database is not connected");
        }
    }

    public static Integer getNextTransactionId() {
        try (Statement statement = DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(transaction_id) as max FROM Transactions");
            if (resultSet.next()) {
                return resultSet.getInt("max") + 1;
            } else {
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database is not connected");
        }
    }

    public static Integer getAccountIdByNameAndCurrency(String name, String currency) {
        try (Statement statement = DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT account_id FROM Accounts JOIN Users ON Users.user_id = Accounts.user_id WHERE name = '%s' AND currency = '%s'", name, currency));
            if (resultSet.next()) {
                return resultSet.getInt("account_id");
            } else {
                throw new RuntimeException("Account for user " + name + " with currency " + currency + " does not exist");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database is not connected");
        }
    }
}
