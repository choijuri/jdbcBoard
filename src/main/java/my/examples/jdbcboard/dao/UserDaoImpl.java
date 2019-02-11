package my.examples.jdbcboard.dao;

import my.examples.jdbcboard.dto.User;
import my.examples.jdbcboard.util.ConnectionContextHolder;
import my.examples.jdbcboard.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDaoImpl  implements UserDao{
    private static UserDao instance = new UserDaoImpl();
    private UserDaoImpl(){}
    public static UserDao getInstance(){
        return instance;
    }


    @Override
    public void addUser(User user) {

        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = ConnectionContextHolder.getConnection();
            //String sql ="INSERT INTO USER( name, passwd, email, nickname) VALUES(?,?,?,?);";
            ps = conn.prepareStatement(UserDaoSQL.INSERT);
            ps.setString(1,user.getName());
            ps.setString(2,user.getPasswd());
            ps.setString(3,user.getEmail());
            ps.setString(4,user.getNickname());
            ps.executeUpdate();


        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            DBUtil.close(ps);
        }

    }


    @Override
    public String getPasswdByEmail(String email) {
        String passwd = null;

        Connection conn = ConnectionContextHolder.getConnection();
        //String sql ="SELECT passwd FROM user WHERE email = ?";
        try(PreparedStatement ps = conn.prepareStatement(UserDaoSQL.SELECT_BY_EMAIL)){
            ps.setString(1, email);

          try(ResultSet rs = ps.executeQuery()){
              if(rs.next()){
                  passwd  = rs.getString(1);

              }
          }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return passwd;
    }


    @Override
    public User getUserByEmail(String email) {
        User user = null; // return할 타입을 선언한다.
        Connection conn = null;

        try {
            conn = conn = ConnectionContextHolder.getConnection();

            try(PreparedStatement ps = conn.prepareStatement(UserDaoSQL.SELECT_USER_BY_EMAIL)) {

                ps.setString(1, email);

                try(ResultSet rs = ps.executeQuery();) {
                    if (rs.next()) {
                        Long id = rs.getLong(1);
                        String name = rs.getString(2);
                        String nickname = rs.getString(3);
                        String passwd = rs.getString(4);

                        user = new User(id, name, nickname, passwd, email);
                    }
                }
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return user;

    }
}
