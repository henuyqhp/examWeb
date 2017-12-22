package com.henu.filter;

import com.henu.util.Const;
import com.henu.util.enums.UserType;
import com.henu.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter{
    private static Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    private String passUrl = "";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        passUrl = filterConfig.getInitParameter("passUrl");
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Const.USER);
        System.out.println(1);
        String[] strArray = passUrl.split(";");

        for (String str : strArray) {
            System.out.println("parsUrl---------:" + str);
            System.out.println(request.getRequestURL() + "** "  +(request.getRequestURL().indexOf(passUrl) > 0));
            System.out.println(request.getRequestURI());
            System.out.println(request.getServletPath() + "++ " + request.getServletPath().contains(passUrl));
            if (str.equals(""))
                continue;
            if (request.getServletPath().contains(passUrl) || request.getRequestURL().indexOf(passUrl) > 0) {
                if (user != null){
                    if(user.getType() == UserType.教师.getCode() && user.getAdmin() == 0)
                        response.sendRedirect("/teacher/index_teacher.jsp");
                    else if (user.getType() == UserType.教师.getCode() && user.getAdmin() == 1 ){
                        response.sendRedirect("/admin/index_admin.jsp");
                    }else{
                        response.sendRedirect("/student/index_student.jsp");
                    }
                    return;
                }
                filterChain.doFilter(request, response);
                return;
            }
        }
        System.out.println(2);
        try{
            if (user != null){
                System.out.println(3);
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                System.out.println(4);
                response.sendRedirect( "/");
                return;
            }
        }catch (Exception e){
            logger.error("嘤嘤嘤，登录过滤器出错啦！T.T");
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
