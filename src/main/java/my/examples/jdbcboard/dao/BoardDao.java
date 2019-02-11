package my.examples.jdbcboard.dao;


import my.examples.jdbcboard.dto.Board1;

import java.util.List;

// Dao : Data Access Object
public interface BoardDao {
    Board1 getBoard(Long id);
    List<Board1> getBoards(int start, int limit);
    void addBoard(Board1 board);
    void updateReadCount(Long id);
    void deleteBoard(long id, long signedId);
    void updateBoard(Board1 board);
    void updateThreadMinus(Long thread);
    void addReply(Board1 board);
    //Board1 getThredDepth(Long parentId);
    void updateThreadPlus(Long min, Long max);
    boolean existReply(Long thread);

    //manage 테이블
    Long getMaxThread();
    Long getCountBoard();
    void updateMaxThread();
    void updateMaxThreadMinus();
    void updateCountBoard();
    void updateCountBoardMinus();

}
