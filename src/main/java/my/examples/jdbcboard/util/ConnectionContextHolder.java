package my.examples.jdbcboard.util;

import java.sql.Connection;

//connection을 threadlocal에 담아 같은 thread에서 이용가능
public class ConnectionContextHolder {
    private static ThreadLocal<Connection> threadLocal
            = new ThreadLocal<>();

    public static void setConnection(Connection connection) {
        threadLocal.set(connection);
    }

    public static Connection getConnection(){
        return threadLocal.get();
    }
}
