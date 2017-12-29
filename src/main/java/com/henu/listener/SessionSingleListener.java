package com.henu.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;
import java.util.Hashtable;

public class SessionSingleListener  implements HttpSessionListener {
    Logger logger = LoggerFactory.getLogger(SessionSingleListener.class);

    public void sessionCreated(HttpSessionEvent se) {
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        String id = session.getId();
        ServletContext context = se.getSession().getServletContext();
        Hashtable hashtable = (Hashtable) context.getAttribute("idlist");
        HashMap hashMap = (HashMap) context.getAttribute("sessionid");
        Object objname = hashMap.get(id);
        if (objname == null)
            return;
        String useid = objname.toString();
        logger.info("hashtable长度 " + hashtable.size() + " hashMap的长度 " + hashMap.size());
        try {
            hashtable.remove(useid);
            hashMap.remove(id);
        }catch (Exception e){
            logger.info("继续");
        }
        logger.info("Session 过期 删除Context中的 " + useid + " 项");
        hashtable = (Hashtable) context.getAttribute("idlist");
        logger.info("hashtable长度 " + hashtable.size() + " hashMap的长度 " + hashMap.size());
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
    }
}