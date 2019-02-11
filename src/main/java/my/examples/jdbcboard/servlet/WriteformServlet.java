package my.examples.jdbcboard.servlet;

import my.examples.jdbcboard.dto.Board1;
import my.examples.jdbcboard.dto.User;
import my.examples.jdbcboard.service.BoardService;
import my.examples.jdbcboard.service.BoardServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "WriteformServlet", urlPatterns = "/write")
public class WriteformServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher requestDispatcher =
                req.getRequestDispatcher("/WEB-INF/views/writeform.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        User user = (User) req.getSession().getAttribute("loginuser");


        BoardService boardService = new BoardServiceImpl();
        Board1 board = new Board1();
        board.setTitle(title);
        board.setContent(content);
        board.setUserId(user.getId());
        board.setNickname(user.getNickname());
        boardService.addBoard(board);

        resp.sendRedirect("/board");
    }
}
