package my.examples.jdbcboard.dao;

public class UserDaoSQL {
    public static final String INSERT =
            "INSERT INTO USER( name, passwd, email, nickname) VALUES(?,?,?,?);";

    public static final String SELECT_BY_EMAIL =
            "SELECT passwd FROM user WHERE email = ?";

    public static final String SELECT_USER_BY_EMAIL =
            "SELECT id, name, nickname, passwd FROM user WHERE email = ?";
}
