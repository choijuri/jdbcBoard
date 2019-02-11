package my.examples.jdbcboard.util;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//중복되는 코드를 없애기 위해 DB에 관한 내용의 객체를 생성한다.
public class DBUtil {
    private static HikariConfig config = null;
    private static DataSource ds = null;
    private static DBUtil instance = new DBUtil();


    private DBUtil() {

        String configFile = "/datasource.properties";
        HikariConfig config = new HikariConfig(configFile);

        ds = new HikariDataSource(config);
    }

    public static DBUtil getInstance(){
        return instance;
    }



    public Connection getConnection(){
        Connection conn = null;
        try{
            conn = ds.getConnection();
            conn.setAutoCommit(false);
        }catch (Exception ex){
            ex.printStackTrace(); //로그를 남기는 코드가 있어야 함.
            throw new RuntimeException("DB 연결을 할 수 없습니다");
        }
        return conn;
    }

    public static void rollback(Connection conn){
        try{ conn.rollback(); } catch(Exception ignore){}
    }

    public static void close(Connection conn) {
        try{ conn.close(); }catch (Exception ignore){}

    }
    public static void close(ResultSet rs, PreparedStatement ps){
        try{ rs.close(); }catch (Exception ignore){}
        close(ps);
    }

    public static void close(PreparedStatement ps){
        try{ ps.close();}catch (Exception ignore){}

    }

}
