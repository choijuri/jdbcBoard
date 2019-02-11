package my.examples.jdbcboard.dao;

import my.examples.jdbcboard.dto.Board1;
import my.examples.jdbcboard.dto.User;

public interface UserDao {
    void addUser(User user);
    User getUserByEmail(String email);
    String getPasswdByEmail(String email);
}
