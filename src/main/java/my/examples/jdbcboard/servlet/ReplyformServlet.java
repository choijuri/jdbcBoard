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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ReplyServlet", urlPatterns = "/reply")
public class ReplyformServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        req.setAttribute("id",Long.parseLong(req.getParameter("id")));
        // if(session.getAttribute("loginuser") == null)

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/replyform.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loginuser");

        //hidden tag로 replyformServlet에서 원글의 id를 넘겨받음
        Long parentId = Long.parseLong((req.getParameter("parent-id")));
        String title = req.getParameter("title");
        String content = req.getParameter("content");

        Board1 board = new Board1(user.getId(), user.getNickname(), title, content);
        BoardService boardService = new BoardServiceImpl();
        boardService.addReply(parentId, board);
        resp.sendRedirect("/board");
    }
}
