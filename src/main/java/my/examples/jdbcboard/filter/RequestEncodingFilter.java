package my.examples.jdbcboard.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


@WebFilter(filterName = "RequestEncodingFilter" , urlPatterns = {"/*"})
public class RequestEncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        //System.out.println("reqeustfilter do filter start!1");
        filterChain.doFilter(servletRequest, servletResponse); //다음필터 호출
        //서블릿 응답후
        //System.out.println("do filter end!!");
    }

    @Override
    public void destroy() {

    }
}
