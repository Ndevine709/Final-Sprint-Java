package org.keyin.user;
public class User {
    private int user_id;
    private String username;
    private String password;
    private String address;
    private String email;
    private String phone_number;
    private String role;

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
}
