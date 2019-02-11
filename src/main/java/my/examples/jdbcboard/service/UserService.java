package my.examples.jdbcboard.service;

import my.examples.jdbcboard.dto.User;

public interface UserService {
    void addUser(User user);
    String getPasswdByEmail(String email);
    User getUserByEmail(String email);

}
