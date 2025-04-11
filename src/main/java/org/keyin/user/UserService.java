package org.keyin.user;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;
import java.util.List;

/**
 * Service class for user management.
 * Handles all business logic for user operations.
 */
public class UserService {
    private UserDao userDao = new UserDao();

    public boolean addUser(User user) {
        if (user.equals(null)) {
            System.out.println("User cannot be null");
            return false;
        }
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashedPassword);
        
        userDao.addUser(user);
        return true;
    }

    /**
     * Logs in a user by their username and password.
     * @param username The username of the user
     * @param password The password of the user
     * @return User object if login successful, null otherwise
     * @throws SQLException if there's an error executing the SQL statement
     */
    public User login(String username, String password) throws SQLException {
        if(username == null || password == null){
            System.out.println("Username or password is null");
            return null;
        }
        User user = userDao.getUserByUsername(username);

        if(user == null){
            System.out.println("User not found");
            return null;
        }

        if(!BCrypt.checkpw(password,user.getPassword())){
            System.out.println("Credentials are incorrect");
            return null;
        }

        System.out.println("User logged in successfully");
        return user;
    }

    /**
     * Deletes a user from the database by their username.
     * @param adminUser The admin user performing the deletion
     * @param usernameToDelete The username of the user to delete
     * @return true if user was successfully deleted, false otherwise
     * @throws SQLException if there's an error executing the SQL statement
     */
    public boolean deleteUser(User adminUser, String usernameToDelete) throws SQLException {
        // Check if the user trying to delete is an admin
        if (adminUser == null || !"ADMIN".equals(adminUser.getRole())) {
            System.out.println("Access denied. Admin privileges required.");
            return false;
        }

        // Don't allow an admin to delete themselves
        if (adminUser.getUsername().equals(usernameToDelete)) {
            System.out.println("Cannot delete your own admin account");
            return false;
        }

        // Check if the user to delete exists
        User userToDelete = userDao.getUserByUsername(usernameToDelete);
        if (userToDelete == null) {
            System.out.println("User not found: " + usernameToDelete);
            return false;
        }

        // Attempt to delete the user
        boolean deleted = userDao.deleteUser(usernameToDelete);
        if (deleted) {
            System.out.println("User deleted successfully: " + usernameToDelete);
        } else {
            System.out.println("Failed to delete user: " + usernameToDelete);
        }
        return deleted;
    }

    /**
     * Views all users in the system.
     * @param adminUser The admin user performing the action
     * @throws SQLException if there's an error executing the SQL statement
     */
    public void viewAllUsers(User adminUser) throws SQLException {
        // Check if the user is an admin
        if (adminUser == null || !"ADMIN".equals(adminUser.getRole())) {
            System.out.println("Access denied. Admin privileges required.");
            return;
        }

        List<User> users = userDao.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found in the system.");
            return;
        }

        System.out.println("\n=== All Users in System ===");
        System.out.println("---------------------------");
        for (User user : users) {
            System.out.println("User ID: " + user.getUserId());
            System.out.println("Username: " + user.getUsername());
            System.out.println("Role: " + user.getRole());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Phone: " + user.getPhoneNumber());
            System.out.println("Address: " + user.getAddress());
            System.out.println("---------------------------");
        }
        System.out.println("Total Users: " + users.size());
    }
}
