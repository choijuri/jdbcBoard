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

@WebServlet(name = "ReadServlet", urlPatterns = "/read")
public class ReadServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        Long id = 0L;
        try{
            id = Long.parseLong(idStr);
        }catch(Exception ex){
            // 오류 화면으로 redirect
            return;
        }

        BoardService boardService = new BoardServiceImpl();
        Board1 board = boardService.getBoard(id);

        if(board == null){
            // 오류 화면으로 redirect
            return;
        }

        req.setAttribute("board", board);

        long signedId = -1;
        HttpSession session = req.getSession();
        if (session.getAttribute("loginuser") != null) {
            User user = (User) (session.getAttribute("loginuser"));
            signedId = user.getId();
        }
        req.setAttribute("signedId", signedId);


        RequestDispatcher requestDispatcher =
                req.getRequestDispatcher("/WEB-INF/views/view.jsp");
        requestDispatcher.forward(req, resp);
    }
}
