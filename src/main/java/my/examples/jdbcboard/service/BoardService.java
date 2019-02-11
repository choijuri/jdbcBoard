package my.examples.jdbcboard.service;

import my.examples.jdbcboard.dto.Board1;

import java.util.List;

public interface BoardService {
    // page에 해당하는 목록을 읽어온다.
    // 전체 건수를 읽어온다.
    // 글을 읽어온다. (글읽기 + 조회수증가)
    // 글을 삭제한다.
    List<Board1> getBoards(int page);
    Board1 getBoard(Long id);
    void deleteBoard(Long id, Long signedId);
    void addBoard(Board1 board);
    void updateBoard(Board1 board);
    void addReply(Long parentId, Board1 board);

}
