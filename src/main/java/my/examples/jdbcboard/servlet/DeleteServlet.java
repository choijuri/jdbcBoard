package my.examples.jdbcboard.servlet;

import my.examples.jdbcboard.dto.User;
import my.examples.jdbcboard.service.BoardService;
import my.examples.jdbcboard.service.BoardServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeleteServlet", urlPatterns = "/delete")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("loginuser");
        Long signedId = user.getId();
        long id = 0L;

        try {
            String idStr = req.getParameter("id");
            id = Long.parseLong(idStr);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        BoardService boardService = new BoardServiceImpl();
        boardService.deleteBoard(id, signedId);
        resp.sendRedirect("/board");
    }
}
