package com.henu.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Hashtable;

public class ContextSingleListener implements ServletContextListener {
    Logger logger = LoggerFactory.getLogger(ContextSingleListener.class);
    //Notification that the web module is ready to process requests
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context=sce.getServletContext();
//这里使用Hashtable因为 Hashtable本身是synchronized 所以为了方便就使用Hashtable
//如果自己编写一个类实现了synchronized 并且只是放入String[只是验证登录名称] 效果会更好
        context.setAttribute("idlist",new Hashtable());
        context.setAttribute("sessionid",new HashMap());
        logger.info("初始化了Context,添加了idlist->Hashtable sessionid->HashMap");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
    }
    //Notification that the servlet context is about to be shut down
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context=sce.getServletContext();
        context.removeAttribute("idlist");
    }
}