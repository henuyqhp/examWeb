package com.henu.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Hashtable;

public class ContextSingleListener implements ServletContextListener {
    Logger logger = LoggerFactory.getLogger(ContextSingleListener.class);


    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context=sce.getServletContext();
        context.setAttribute("idlist",new Hashtable());
        context.setAttribute("sessionid",new HashMap());
        logger.info("初始化了Context,添加了idlist->Hashtable sessionid->HashMap");
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
    }
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context=sce.getServletContext();
        context.removeAttribute("idlist");
    }
}