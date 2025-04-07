package org.keyin.user;
public class User {
    private int user_id;
    private String username;
    private String password;
    private String address;
    private String email;
    private String phone_number;
    private String role;

    // Constructors
    public User (int user_id, String username, String password, String address, String email, String phone_number, String role) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
    }

    public User (String username, String password, String address, String email, String phone_number, String role) {
        this.username = username;
        this.password = password;
        this.address = address;
        this.email = email;
        this.phone_number = phone_number;
        this.role = role;
    }

    // Getters and Setters
    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public void setPhoneNumber(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
