package org.keyin.user;
import org.keyin.database.DatabaseConnection;
import java.sql.*;

public class UserDao {

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


    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        DriverManager DatabaseConnector;
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            resultSet.getInt("user_id"),
                            resultSet.getString("username"),
                            resultSet.getString("password"),
                            resultSet.getString("address"),
                            resultSet.getString("email"),
                            resultSet.getString("phone_number"),
                            resultSet.getString("role")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void getAllUsers() throws SQLException {
        ResultSet resultset = null;
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users")) {
            resultset = preparedStatement.executeQuery();
            System.out.println("\n=== All Users ===");
            while (resultset.next()) {
                System.out.println("User ID: " + resultset.getInt("user_id"));
                System.out.println("Username: " + resultset.getString("username"));
                System.out.println("Password: " + resultset.getString("password"));
                System.out.println("Address: " + resultset.getString("address"));
                System.out.println("Email: " + resultset.getString("email"));
                System.out.println("Phone Number: " + resultset.getString("phone_number"));
                System.out.println("Role: " + resultset.getString("role"));
                System.out.println("------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int userId) throws SQLException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
