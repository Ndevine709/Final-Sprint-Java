package org.keyin.user.childclasses;

import org.keyin.user.User;

public class Member extends User {
    public Member(int user_id, String username, String password, String address, String email, String phone_number) {
        super(user_id, username, password, address, email, phone_number, "MEMBER");
    }
}
