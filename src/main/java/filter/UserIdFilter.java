package filter;

import povo.Users;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserIdFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //获取userId, 如果不存在, 那么进入登入, 如果存在不是管理员, 进入主页;
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        String path = req.getContextPath();
        HttpSession session = req.getSession();
        Users user = (Users) session.getAttribute("userId");
        //判断是否是Ajax
        String type=req.getHeader("X-Requested-With")==null ? "" : req.getHeader("X-Requested-With");
        //处理Ajax, 设置响应头
        if ("XMLHttpRequest".equals(type)) {
            resp.setHeader("REDIRECT", "REDIRECT");
        }
        if (user != null) {
                filterChain.doFilter(req, resp);
        }else {
            resp.sendRedirect(path+"/login.jsp");
        }
    }

    @Override
    public void destroy() {

    }
}
