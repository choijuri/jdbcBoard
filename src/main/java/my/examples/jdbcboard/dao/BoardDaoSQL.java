package my.examples.jdbcboard.dao;

public class BoardDaoSQL {
    public static final String SELECT_BY_ID =
            //"SELECT b.id,b.user_id,b.title,b.content,u.name,b.regdate,b.read_count FROM board b INNER JOIN user u ON b.user_id=u.id where b.id=?";
            //"SELECT id, title, content, nickname, regdate, read_count, user_id FROM board where id = ?";
            "SELECT id, user_id, title, content, nickname, regdate, read_count, depth, thread FROM board where id = ?";
    public static final String SELECT_BY_PAGING =
            //"SELECT b.id,b.user_id,b.title,b.content,u.name,b.regdate,b.read_count FROM board b INNER JOIN user u ON b.user_id=u.id ORDER BY b.thread DESCLIMIT?,?";
            //"SELECT id, user_id, title, content, nickname, regdate, read_count FROM board ORDER BY thread DESC LIMIT ?, ?";
            "SELECT id, user_id, title, nickname, regdate, read_count , depth, thread FROM board ORDER BY thread DESC LIMIT ?, ?";
    public static final String INSERT =
            //"INSERT INTO board (thread, user_id, title, content) VALUES ((SELECT IFNULL(MAX(thread) + 100, 100) FROM board b),? , ?, ?)";
            "INSERT INTO board (thread, user_id, nickname, title, content) VALUES ((SELECT IFNULL(MAX(thread) + 100, 100) FROM board b),? ,?, ?, ?)";
    public static final String UPDATE =
            "UPDATE board SET title = ?, content = ? WHERE id = ?";
    public static final String DELETE =
            "DELETE FROM board WHERE id = ?";
    public static final String UPDATE_READCOUNT =
            "UPDATE board SET read_count = read_count + 1 WHERE id = ?";
    public static final String EXIST_BY_THREAD=
            "SELECT EXISTS(SELECT * FROM board WHERE thread =? limit 1)";
    public static final String INSERT_REPLY =
            "INSERT INTO board (thread, depth, user_id, nickname, title, content) VALUES(?,?,?,?,?,?)";


    public static final String SELECT_MAX_THREAD =
            "SELECT max_thread FROM manage WHERE id = 1";
    public static final String SELECT_COUNT_BOARD=
            "SELECT count_board FROM manage WHERE id = 1";
    public static final String UPDATE_MAX_THREAD =
            "UPDATE manage SET max_thread = max_thread+100 WHERE id =1";
    public static final String UPDATE_COUNT_BOARD =
            "UPDATE manage SET count_board = count_board+1 WHERE id =1";
    public static final String UPDATE_MAX_THREAD_MINUS=
            "UPDATE manage SET max_thread = max_thread-100 WHERE id =1";
    public static final String UPDATE_COUNT_BOARD_MINUS=
            "UPDATE manage SET count_board = count_board-1 WHERE id =1";
}
