package my.examples.jdbcboard.exam;


/*
0건 이상의 목록을 읽어올 때.
mysql> SELECT id, title, content, name, regdate, read_count
    -> FROM board
    -> ORDER BY id DESC LIMIT 0, 3;
+----+--------+----------+------+---------------------+------------+
| id | title  | content  | name | regdate             | read_count |
+----+--------+----------+------+---------------------+------------+
|  8 | title8 | content8 | kim8 | 2019-01-11 02:48:32 |          0 |
|  7 | title7 | content7 | kim7 | 2019-01-11 02:47:58 |          0 |
|  6 | title6 | content6 | kim6 | 2019-01-11 02:47:58 |          0 |
+----+--------+----------+------+---------------------+------------+
3 rows in set (0.00 sec)
 */


import java.sql.*;

public class SelectListExam {
    public static void main(String[] args) {
        // 1건의 데이터를 요청하는 경우
        // 조건에 해당하는 데이터가 없을 수 있다.
        final String driverClassname = "com.mysql.cj.jdbc.Driver"; // driver class이름도 JDBC Driver에 따라 다름
        final String driverURL = "jdbc:mysql://localhost:3306/myboard?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Seoul"; // driver URL형식 Database마다 다르다.

        final String dbUserId = "board_user";
        final String dbUserPassword = "1234";

        long start = 0;


        int limit = 3;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            // a. DB 연결 - Connection
            //    DB연결을 하려면 필요한 정보가 있다. Driver classname, DB URL, DB UserId , DB User Password
            Class.forName(driverClassname); // driver class가 로딩.
            conn = DriverManager.getConnection(driverURL, dbUserId, dbUserPassword);
            if(conn != null) {
                System.out.println("conn ok!");
                System.out.println(conn.getClass().getName());
            }
            // b. SELECT SQL 준비 - Connection
            String sql = "SELECT id, title, content, name, regdate, read_count FROM board ORDER BY id DESC LIMIT ?, ?";
            ps = conn.prepareStatement(sql);
            // c. 바인딩 - PreparedStatement
            ps.setLong(1, start); // 첫번째 물음표
            ps.setInt(2,limit);
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

                System.out.println(id + "," + title + ", " + content + ", " + name + ", " + regdate + ", " + readCount);
            }

        }catch(Exception ex){
            ex.printStackTrace();

        }finally {
            // g. ResultSet, PreparedStatement, Connection 순으로 close 를 한다.
            try{ rs.close(); } catch(Exception ignore){}
            try{ ps.close(); } catch(Exception ignore){}
            try{ conn.close(); } catch(Exception ignore){}
        }
    }
}
