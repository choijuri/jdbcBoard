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
import java.io.IOException;


@WebServlet(name = "JoinServlet", urlPatterns = "/join")
public class JoinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher =
                req.getRequestDispatcher("/WEB-INF/views/join.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String nickname = req.getParameter("nickname");
        String passwd = req.getParameter("userPWD");
        String email = req.getParameter("email");

        PasswordEncoder passwordEncoder =
                PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // 암호화 하는 코드

        String encodePasswd = passwordEncoder.encode(passwd);

        User user = new User(name, nickname, email, encodePasswd);
        UserService userService = UserServiceImpl.getInstance();
        userService.addUser(user);
        resp.sendRedirect("/login");


    }
}
