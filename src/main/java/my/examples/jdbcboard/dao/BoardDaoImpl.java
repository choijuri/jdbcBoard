package my.examples.jdbcboard.dao;


import my.examples.jdbcboard.dto.Board1;
import my.examples.jdbcboard.util.ConnectionContextHolder;
import my.examples.jdbcboard.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BoardDaoImpl implements BoardDao {
    @Override
    public Board1 getBoard(Long idParam) {
        Board1 board = null;


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
         try{
             // a. DB 연결 - Connection
             //    DB연결을 하려면 필요한 정보가 있다. Driver classname, DB URL, DB UserId , DB User Password
             conn =  ConnectionContextHolder.getConnection();

             // b. SELECT SQL 준비 - Connection
             //String sql = "SELECT b.id,b.user_id, b.title, b.content, u.name, b.regdate, b.read_count FROM board b INNER JOIN user u ON b.user_id = u.id where b.id = ?";
             ps = conn.prepareStatement(BoardDaoSQL.SELECT_BY_ID);

             // c. 바인딩 - PreparedStatement
             ps.setLong(1, idParam);

             // d. SQL 실행 - PreparedStatement
             rs = ps.executeQuery();

             // e. 1건의 row를 읽어온다. row는 여러개의 컬럼으로 구성되어 있다. - ResultSet
             // f. e에서 읽어오지 못하는 경우도 있다.

             //id, title, content, nickname, regdate, read_count
             if(rs.next()){
                 long id = rs.getLong(1);
                 long userId = rs.getLong(2);
                 String title = rs.getString(3);
                 String content = rs.getString(4);
                 String nickname = rs.getString(5);
                 Date regdate = rs.getDate(6);
                 int readCount = rs.getInt(7);
                 int depth = rs.getInt(8);
                 long thread = rs.getLong(9);

                //long id, long thread, int depth, long userId, String nickname, String title, String content, Date regdate, int readCount
                 board = new Board1(id, userId, nickname, title, content, regdate, readCount);
                 board.setDepth(depth);
                 board.setThread(thread);
                 //id, title, content, nickname, regdate, readCount
             }

         }catch (Exception ex){
            ex.printStackTrace();
         }finally {
             DBUtil.close(rs,ps);
         }


        return board;
    }

    @Override
    public List<Board1> getBoards(int start, int limit) {
        List<Board1> boardlist = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            // a. DB 연결 - Connection
            conn = ConnectionContextHolder.getConnection();
            // b. SELECT SQL 준비 - Connection
            //String sql = " SELECT b.id, b.user_id, b.title, b.content, u.name, b.regdate, b.read_count FROM board b INNER JOIN user u ON b.user_id = u.id ORDER BY b.thread DESC LIMIT ?, ?";
            ps = conn.prepareStatement(BoardDaoSQL.SELECT_BY_PAGING);

            // c. 바인딩 - PreparedStatement
            ps.setLong(1, start); // 첫번째 물음표에 5를 바인딩한다.
            ps.setInt(2, limit);

            // d. SQL 실행 - PreparedStatement
            rs = ps.executeQuery(); // SELECT 문장을 실행, executeUpdate() - insert, update, delete

            // e. 1건의 row를 읽어온다. row는 여러개의 컬럼으로 구성되어 있다. - ResultSet
            // f. e에서 읽어오지 못하는 경우도 있다.
            while(rs.next()){

                //SELECT id, user_id, title, content, nickname, regdate, read_count
                long id = rs.getLong(1);
                long userId = rs.getLong(2);
                String title = rs.getString(3);
                String nickname = rs.getString(4);
                Date regdate = rs.getDate(5);
                int readCount = rs.getInt(6);
                int depth = rs.getInt(7);
                long thread = rs.getLong(8);

                Board1 board = new Board1(id, userId, nickname, title, regdate, readCount, depth);
                board.setThread(thread);
                boardlist.add(board);
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            DBUtil.close(rs, ps);
        }

        return boardlist;
    }

    @Override
    public void addBoard(Board1 board) {
        Connection conn = null;
        PreparedStatement ps = null;
        try{
            conn = ConnectionContextHolder.getConnection();
            //String sql = "INSERT INTO board (thread, user_id, title, content) VALUES ((SELECT IFNULL(MAX(thread) + 100, 100) FROM board b),?, ?, ?);";
            ps = conn.prepareStatement(BoardDaoSQL.INSERT);
            ps.setLong(1, board.getUserId());
            ps.setString(2, board.getNickname());
            ps.setString(3, board.getTitle());
            ps.setString(4, board.getContent());
            ps.executeUpdate(); // 입력,수정,삭제 건수 가 리턴된다.
        }catch(Exception ex){
            ex.printStackTrace();
        }finally {
            DBUtil.close(ps);
        }
    }


    @Override
    public void updateReadCount(Long id) {
        Connection conn =null;
        PreparedStatement ps = null;
        try{
            conn =ConnectionContextHolder.getConnection();
            //String sql = "UPDATE board SET read_count = read_count + 1 WHERE id = ?";
            ps = conn.prepareStatement(BoardDaoSQL.UPDATE_READCOUNT);
            ps.setLong(1, id);

            ps.executeUpdate(); // 입력,수정,삭제 건수 가 리턴된다.
        }catch (Exception ex){
            ex.printStackTrace();
        }finally {
            DBUtil.close(ps);
        }
    }


    @Override
    public void deleteBoard(long id, long signedId) {
        try{
            Connection conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.DELETE)) {
                ps.setLong(1,id);
                ps.executeUpdate(); // 입력,수정,삭제 건수 가 리턴된다.
            }
        }catch (Exception ex){
                ex.printStackTrace();
            }
        }




    @Override
    public void updateBoard(Board1 board) {
        Connection conn = null;
        try{
            conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.UPDATE)){
                System.out.println(board);

                //"UPDATE board SET title = ?, content = ?, WHERE id = ?";
                ps.setString(1,board.getTitle());
                ps.setString(2,board.getContent());
                ps.setLong(3,board.getId());
                ps.executeUpdate();

            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }


    @Override
    public void updateThreadMinus(Long thread) {
        Connection conn =null;
        try{
            conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.UPDATE_MAX_THREAD_MINUS)){
                ps.setLong(1,(thread/100)*100); //min
                ps.setLong(2,thread+1); //max
                ps.executeUpdate();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void addReply(Board1 board) {
        Connection conn = null;

        try{
            conn =  ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.INSERT_REPLY)){
                //thread, depth, user_id, nickname, title, content
                ps.setLong(1, board.getThread());
                ps.setInt(2,board.getDepth());
                ps.setLong(3,board.getUserId());
                ps.setString(4,board.getNickname());
                ps.setString(5,board.getTitle());
                ps.setString(6, board.getContent());

                ps.executeUpdate();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void updateCountBoard(){
        Connection conn = null;

        try{
            conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.UPDATE_COUNT_BOARD)){
                ps.executeUpdate();
            }

        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void updateThreadPlus(Long min, Long max) {

    }

    @Override
    public boolean existReply(Long thread) {
        boolean flag = false;
        Connection conn = null;
        try{
            conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.EXIST_BY_THREAD);){
                ps.setLong(1,thread-1);
                try(ResultSet rs = ps.executeQuery();){
                    if(rs.next()){
                        int result = rs.getInt(1);
                        flag = result ==1? true : false;
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return flag;
    }

    @Override
    public Long getMaxThread() {
        Long thread = 0L;
        Connection conn = null;
        try{
            conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.SELECT_MAX_THREAD)){
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next())
                        thread = rs.getLong(1);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return thread;
    }

    @Override
    public Long getCountBoard() {
        long count = 0;
        Connection conn = null;
        try{
            conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.SELECT_COUNT_BOARD)){
                try(ResultSet rs = ps.executeQuery()){
                    if(rs.next())
                        count = rs.getLong(1);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public void updateMaxThread() {
        Connection conn = null;
        try{
            conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.UPDATE_MAX_THREAD)){
                ps.executeUpdate();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void updateMaxThreadMinus() {
        Connection conn = null;
        try{
            conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.UPDATE_MAX_THREAD_MINUS)){
                ps.executeUpdate();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }

    @Override
    public void updateCountBoardMinus() {
        Connection conn = null;
        try{
            conn = ConnectionContextHolder.getConnection();
            try(PreparedStatement ps = conn.prepareStatement(BoardDaoSQL.UPDATE_COUNT_BOARD_MINUS)){
                ps.executeUpdate();
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
