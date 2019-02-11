package my.examples.jdbcboard.exam;


import my.examples.jdbcboard.dto.Board1;
import my.examples.jdbcboard.dao.BoardDao;
import my.examples.jdbcboard.dao.BoardDaoImpl;

// 1건의 데이터를 요청하는 경우
// 조건에 해당하는 데이터가 없을 수 있다.
public class SelectOneExam1 {
    public static void main(String[] args){
        BoardDao boardDao = new BoardDaoImpl();
        Board1 board = boardDao.getBoard(5L);
        System.out.println(board);
    }

}
