package my.examples.jdbcboard.service;

import my.examples.jdbcboard.dao.BoardDao;
import my.examples.jdbcboard.dao.BoardDaoImpl;
import my.examples.jdbcboard.dto.Board1;
import my.examples.jdbcboard.util.ConnectionContextHolder;
import my.examples.jdbcboard.util.DBUtil;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class BoardServiceImpl implements BoardService {
    public static final int SIZE = 5;

    @Override
    public List<Board1> getBoards(int page) {
        BoardDao boardDao = new BoardDaoImpl();
        List<Board1> boards = new ArrayList<>();

        int start = page * SIZE - SIZE;
        int limit = SIZE;

        try(Connection conn = DBUtil.getInstance().getConnection()){
            ConnectionContextHolder.setConnection(conn);
            boards = boardDao.getBoards(start, limit);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return boards;
    }

    public long getCountBoard(){
        long count = 0L;
        BoardDao boardDao = new BoardDaoImpl();
        Connection conn = null;
        try{
            conn = DBUtil.getInstance().getConnection();
            ConnectionContextHolder.setConnection(conn);
            count = boardDao.getCountBoard();
        }catch (Exception ex){
            DBUtil.rollback(conn);
            ex.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
        return count;
    }


    @Override
    public Board1 getBoard(Long id) {
        Board1 board = null;
        Connection conn = null;
        BoardDao boardDao = new BoardDaoImpl();
        try{
            conn = DBUtil.getInstance().getConnection();
            ConnectionContextHolder.setConnection(conn);
            board = boardDao.getBoard(id);
            boardDao.updateReadCount(id);
            conn.commit(); // 트랜젝션 commit

        }catch(Exception ex){
            DBUtil.rollback(conn);
            ex.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }
        return board;
    }

    @Override
    public void deleteBoard(Long id, Long signedId) {
        BoardDao boardDao= new BoardDaoImpl();
        Connection conn = null;
        try{
            conn = DBUtil.getInstance().getConnection();
            ConnectionContextHolder.setConnection(conn);
            Board1 board = boardDao.getBoard(id);
            if(board.getUserId() == signedId){
                if(!boardDao.existReply(board.getThread())){
                    boardDao.deleteBoard(id, signedId);
                    boardDao.updateCountBoardMinus();
                    if(board.getThread() == boardDao.getMaxThread())
                        boardDao.updateMaxThreadMinus();
                }
            }
            conn.commit(); // 트랜젝션 commit
        }catch (Exception ex){
            DBUtil.rollback(conn);
            ex.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }


    }

    @Override
    public void addBoard(Board1 board) {
        BoardDao boardDao= new BoardDaoImpl();
        Connection conn = null;
         try{
            conn = DBUtil.getInstance().getConnection();
            ConnectionContextHolder.setConnection(conn);
            boardDao.addBoard(board); //board추가
            boardDao.updateMaxThread(); //max_thread+100
            boardDao.updateCountBoard(); //count_board+1
            conn.commit(); // 트랜젝션 commit
         }catch (Exception ex){
            DBUtil.rollback(conn);
            ex.printStackTrace();
         }finally {
            DBUtil.close(conn);
         }

    }

    @Override
    public void updateBoard(Board1 board) {
        BoardDao boardDao= new BoardDaoImpl();
        Connection conn = null;
        try{
            conn = DBUtil.getInstance().getConnection();
            ConnectionContextHolder.setConnection(conn);
            boardDao.updateBoard(board);
            conn.commit(); // 트랜젝션 commit
        }catch (Exception ex){
            DBUtil.rollback(conn);
            ex.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }

    }

    @Override
    public void addReply(Long parentId, Board1 board) {
        BoardDao boardDao = new BoardDaoImpl();
        Connection conn = null;
        try{
            conn = DBUtil.getInstance().getConnection();
            ConnectionContextHolder.setConnection(conn);

            // 1. 부모의 thread, depth를 가져온다.
            Board1 tmpBoard = boardDao.getBoard(parentId);
            Long thread = tmpBoard.getThread();
            int depth = tmpBoard.getDepth();

            // 2. thread UPDATE
            boardDao.updateThreadMinus(thread);

            // 3. board 설정 thred는 -1, depth는 +1
            board.setThread(thread - 1);
            board.setDepth(depth + 1);
            boardDao.addReply(board);

            // 4.manage UPDATE
            boardDao.updateCountBoard();
            conn.commit();

        }catch (Exception ex){
            DBUtil.rollback(conn);
            ex.printStackTrace();
        }finally {
            DBUtil.close(conn);
        }


    }
}
