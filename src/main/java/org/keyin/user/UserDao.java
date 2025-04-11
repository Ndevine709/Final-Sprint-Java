package org.keyin.user;

import org.keyin.database.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for User entities.
 * Handles all database operations for users including CRUD operations.
 */
public class UserDao {

    /**
     * Adds a new user to the database.
     * @param user The user object to be added
     */
    public void addUser(User user) {
        String sql = "INSERT INTO users (username, password, address, email, phone_number, role) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getAddress());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPhoneNumber());
            preparedStatement.setString(6, user.getRole());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a user from the database by their username.
     * @param username The username to search for
     * @return User object if found, null if not found
     * @throws SQLException if there's an error executing the SQL statement
     */
    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("address"),
                            resultSet.getString("email"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("role"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all users from the database.
     * @return List of all users in the database
     * @throws SQLException if there's an error executing the SQL statement
     */
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
                ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("address"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return users;
    }

    /**
     * Deletes a user from the database by their username.
     * @param username The username of the user to delete
     * @return true if user was successfully deleted, false if user was not found or deletion failed
     * @throws SQLException if there's an error executing the SQL statement
     */
    public boolean deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
