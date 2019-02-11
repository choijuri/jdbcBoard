
/*
package my.examples.jdbcboard.exam;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import my.examples.jdbcboard.dto.Board1;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class HikariCPExam {
    public static void main(String[] args){

        // src/main/resources 아래에 datasource.properties를 위치시킨다.
        // src/main/resources 이 경로도 CLASSPATH
        // classpath에 있는 자원을 읽어들이려면 / 로 시작한다.
        String configFile = "/datasource.properties";
        HikariConfig config = new HikariConfig(configFile);

//        HikariConfig config = new HikariConfig();
//        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        config.setJdbcUrl("jdbc:mysql://localhost:3306/myboard?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul");
//        config.setUsername("board_user");
//        config.setPassword("1234");

        DataSource ds = new HikariDataSource(config);

        int start = 0;
        int limit = 4;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = ds.getConnection();

            if (conn != null)
                System.out.println("connection is ok");

            String sql = "SELECT id, title, content, name, regdate, read_count FROM board ORDER BY id DESC LIMIT ?, ?";
            ps = conn.prepareStatement(sql);
            // c. 바인딩 - PreparedStatement
            ps.setLong(1, start); // 첫번째 물음표에 5를 바인딩한다.
            ps.setInt(2, limit);

            // d. SQL 실행 - PreparedStatement
            rs = ps.executeQuery(); // SELECT 문장을 실행, executeUpdate() - insert, update, delete

            // e. 1건의 row를 읽어온다. row는 여러개의 컬럼으로 구성되어 있다. - ResultSet
            // f. e에서 읽어오지 못하는 경우도 있다.
            while(rs.next()){
                long id = rs.getLong(1);
                String title = rs.getString(2);
                String content = rs.getString(3);
                String name = rs.getString(4);
                Date regdate = rs.getDate(5);
                int readCount = rs.getInt(6);

                Board1 board = new Board1(id ,title, content, name,regdate, readCount);
                System.out.println(board);
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            try{
                rs.close();
            }catch(Exception ignore){}
            try{
                ps.close();
            }catch(Exception ignore){}
            try{
                conn.close();
            }catch(Exception ignore){}
        }

    }

}


*/