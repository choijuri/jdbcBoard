package my.examples.jdbcboard.servlet;

import my.examples.jdbcboard.dto.Board1;
import my.examples.jdbcboard.service.BoardService;
import my.examples.jdbcboard.service.BoardServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BoardServlet", urlPatterns = "/board")
public class BoardServlet extends HttpServlet {
    private static final int SIZE = 5; // 설정파일에서 읽어들이도록 수정한다.
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1. page 값을 파라미터로 읽어들인다. 값이 없으면 기본값은 1페이지로 한다.
        String pageStr = req.getParameter("page");
        int page = 1;
        try{
            page = Integer.parseInt(pageStr);
        }catch(Exception ignore){}

        // 3. db에서 목록을 읽어들인다.
        BoardService boardService = new BoardServiceImpl();
        List<Board1> boards = boardService.getBoards(page);

        for(Board1 b : boards){
            int depth = b.getDepth();
            String title = b.getTitle();
            for(int i=0; i<depth; i++){
                title = "re: &nbsp;" +title;
            }
            b.setTitle(title);
        }

        // 4. request에 3에서 구한값을 setAttribute로 담아서 jsp에게 전달한다.
        req.setAttribute("boards", boards);
        // 5. jsp에서는 jstl과 el로 결과를 출력한다.

        RequestDispatcher requestDispatcher =
                req.getRequestDispatcher("/WEB-INF/views/board.jsp");
        requestDispatcher.forward(req, resp);
    }
}
