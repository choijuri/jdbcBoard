package my.examples.jdbcboard.servlet;

import my.examples.jdbcboard.dto.User;
import my.examples.jdbcboard.service.UserService;
import my.examples.jdbcboard.service.UserServiceImpl;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher =
                req.getRequestDispatcher("/WEB-INF/views/login.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        String passwd = req.getParameter("userPWD");

        UserService userService = UserServiceImpl.getInstance();
        String encodingPasswd = userService.getPasswdByEmail(email);
        User user = userService.getUserByEmail(email);

        if(user != null && encodingPasswd != null){
            PasswordEncoder passwordEncoder =
            PasswordEncoderFactories.createDelegatingPasswordEncoder();

            boolean matches = passwordEncoder.matches(passwd, encodingPasswd);

            if(matches){
                //로그인 정보 세션에 저장
                HttpSession session = req.getSession();
                session.setAttribute("loginuser",user);

            }else{
                resp.sendRedirect("/login");
                return;
            }
        }
        if(req.getParameter("redirect") != ""){
            resp.sendRedirect("/"+req.getParameter("redirect"));
            return;
        }
        resp.sendRedirect("/board");

    }
}
