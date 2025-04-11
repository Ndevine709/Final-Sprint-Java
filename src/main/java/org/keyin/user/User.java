package org.keyin.user;

/**
 * Represents a user in the Gym Management System.
 * This class stores all user-related information including personal details and authentication data.
 */
public class User {
    private int user_id;
    private String username;
    private String password;
    private String address;
    private String email;
    private String phone_number;
    private String role;

    /**
     * Constructs a new User with all fields including user ID.
     * @param user_id The unique identifier for the user
     * @param username The user's chosen username
     * @param password The user's password
     * @param address The user's physical address
     * @param email The user's email address
     * @param phone_number The user's contact phone number
     * @param role The user's role in the system (ADMIN, TRAINER, or MEMBER)
     */
    public User (int user_id, String username, String password, String address, String email, String phone_number, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
    }

    /**
     * Constructs a new User without an ID (used for new user creation).
     * @param username The user's chosen username
     * @param password The user's password
     * @param address The user's physical address
     * @param email The user's email address
     * @param phone_number The user's contact phone number
     * @param role The user's role in the system (ADMIN, TRAINER, or MEMBER)
     */
    public User (String username, String password, String address, String email, String phone_number, String role) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
    }

    /**
     * Gets the user's unique identifier.
     * @return The user's ID
     */
    public int getUserId() {
        return user_id;
    }

    /**
     * Sets the user's unique identifier.
     * @param user_id The ID to set
     */
    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    /**
     * Gets the user's username.
     * @return The username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's username.
     * @param username The username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the user's password.
     * @return The password (hashed)
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password.
     * @param password The password to set (will be hashed before storage)
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the user's address.
     * @return The physical address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the user's address.
     * @param address The address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the user's email address.
     * @return The email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the user's email address.
     * @param email The email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets the user's phone number.
     * @return The phone number
     */
    public String getPhoneNumber() {
        return phone_number;
    }

    /**
     * Sets the user's phone number.
     * @param phone_number The phone number to set
     */
    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    /**
     * Gets the user's role.
     * @return The role (ADMIN, TRAINER, or MEMBER)
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the user's role.
     * @param role The role to set (should be ADMIN, TRAINER, or MEMBER)
     */
    public void setRole(String role) {
        this.role = role;
    }
}
