package com.henu.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionSingleFilter implements Filter {
    private FilterConfig filterConfig;
    private Logger logger = LoggerFactory.getLogger(SessionSingleFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) servletRequest;
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            Object obj = request.getAttribute("user");
            logger.info("4-----------------------");
//            if (obj == null){
//                response.sendRedirect("/");
//                logger.info("4--------------------**");
//                return;
//            }
            logger.info("4----------------------9999-");
            filterChain.doFilter(servletRequest,servletResponse);
        }catch (Exception e){
            logger.error("{},系统出错",e);
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {

    }
}
