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

@WebServlet(name = "UpdateformServlet", urlPatterns = "/update")
public class UpdateformServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        BoardService boardService = new BoardServiceImpl();
        Board1 board = boardService.getBoard(Long.parseLong(req.getParameter("id")));

        if(board == null){
            return;
        }

        req.setAttribute("board", board);
        User user = (User)session.getAttribute("loginuser");
        if(user.getId() != board.getUserId()){
           resp.sendRedirect("/board");
        }else{
            RequestDispatcher requestDispatcher =req.getRequestDispatcher("/WEB-INF/views/updateform.jsp");
            requestDispatcher.forward(req,resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loginuser");



        Long id = Long.parseLong(req.getParameter("id"));
        String title = req.getParameter("title");
        String content = req.getParameter("content");
        BoardService boardService = new BoardServiceImpl();
        Board1 board = new Board1();
        board.setId(id);
        board.setTitle(title);
        board.setContent(content);
        board.setUserId(user.getId());
        board.setNickname(user.getNickname());
//        Board1 board = new Board1(user.getId(), user.getNickname(),title,content);
//        board.setId(id);

        boardService.updateBoard(board);
        resp.sendRedirect("/board");
    }
}
