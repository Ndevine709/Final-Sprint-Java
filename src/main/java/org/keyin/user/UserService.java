package org.keyin.user;
import org.mindrot.jbcrypt.BCrypt;
import java.sql.SQLException;

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
        System.out.println("User added successfully!");
        return true;
    }

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
}
