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
        String[] strArray = passUrl.split(";");
        System.out.println(1);
        for (String str : strArray) {
            System.out.println("/*/");
            if (str.equals("")){
                System.out.println("/*/----");
                continue;
            }
            if (request.getServletPath().contains(passUrl) || request.getRequestURL().indexOf(passUrl) > 0) {
                System.out.println("user is null");
                if (user != null){
                    if(user.getType() == UserType.教师.getCode() && user.getAdmin() == 0){
                        System.out.println(2);
                        response.sendRedirect("/teacher/index_teacher.jsp");
                    }
                    else if (user.getType() == UserType.管理员.getCode() && user.getAdmin() == 1 ){
                        System.out.println(3);
                        response.sendRedirect("/admin/index_admin.jsp");
                    }else if(user.getType() == UserType.学生.getCode()){
                        System.out.println(4);
                        response.sendRedirect("/student/index_student.jsp");
                    }
                    System.out.println(user.toString());
                    return;
                }
                System.out.println(5);
                filterChain.doFilter(request, response);
                return;
            }
            System.out.println("//////////////");
        }
        try{
            if (user != null){
                System.out.println(6);
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                System.out.println(7);
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
