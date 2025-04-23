package org.elena.test2.dao;

import org.elena.test2.dto.UserDto;
import org.elena.test2.utils.ConnectionStringUtil;

import java.sql.*;

public class UserDao {
    public static final String ADD_USER_WITHOUT_ADDRESS_QUERY = "INSERT INTO Users (user_id, name) VALUES (?, ?)";
    public static final String ADD_USER_WITH_ADDRESS_QUERY = "INSERT INTO Users (user_id, name, address) VALUES (?, ?, ?)";

    public static void createUserWithoutAddress(UserDto userDto) {
        try (PreparedStatement preparedStatement =
                     DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).prepareStatement(ADD_USER_WITHOUT_ADDRESS_QUERY)) {
            preparedStatement.setInt(1, getNextUserId());
            preparedStatement.setString(2, userDto.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database is not connected");
        }
    }

    public static void createUserWithAddress(UserDto userDto) {
        try (PreparedStatement preparedStatement =
                     DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).prepareStatement(ADD_USER_WITH_ADDRESS_QUERY)) {
            preparedStatement.setInt(1, getNextUserId());
            preparedStatement.setString(2, userDto.getName());
            preparedStatement.setString(3, userDto.getAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database is not connected");
        }
    }

    public static Integer getNextUserId() {
        try (Statement statement = DriverManager.getConnection(ConnectionStringUtil.getConnectionString()).createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT MAX(user_id) as max FROM Users");
            if (resultSet.next()) {
                return resultSet.getInt("max") + 1;
            } else {
                return 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database is not connected");
        }
    }
}
