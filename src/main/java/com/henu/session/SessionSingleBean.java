package com.henu.session;

import com.henu.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Hashtable;

public class SessionSingleBean {
    private static Logger logger = LoggerFactory.getLogger(SessionSingleBean.class);
    private SessionSingleBean(){}
    private static SessionSingleBean sessionSingleBean ;
    public static SessionSingleBean getInstance(){
        if (sessionSingleBean == null){
            synchronized (SessionSingleBean.class){
                if (sessionSingleBean == null){
                    sessionSingleBean = new SessionSingleBean();
                }
            }
        }
        return sessionSingleBean;
    }

    /**
     * 在session缓存中检验是否已经登录，
     * @param request　请求
     * @param userid　用户username
     * @return false:用户已经登录，true:用户未登录，加入缓存
     */
    public boolean checkSession(HttpServletRequest request,String userid){
        boolean isSession = true;
        Hashtable hashtable = (Hashtable) request.getSession().getServletContext().getAttribute("idlist");
        HashMap hashMap = (HashMap) request.getSession().getServletContext().getAttribute("sessionid");
        synchronized (this){
            Object obj = hashtable.get(userid);
            if (obj != null){
                User user = (User) obj;
                isSession = false;
                logger.info("{},已经存在--------",userid);

            }else{
                logger.info(">>>>>>>>>>>>>>>>>>>>>");
                User newUser = new User();
                newUser.setId(Integer.parseInt(userid));
                hashtable.put(userid,newUser);
                hashMap.put(request.getSession().getId(),userid);
                logger.info("{},已经放入上下文--------",userid);
                logger.info(">>>>>>>>>>>>>>>>>>>>>");
            }
        }
        return isSession;
    }

    public boolean removeStudent(HttpServletRequest request,Integer sno){
        ServletContext context = request.getSession().getServletContext();
        Hashtable hashtable = (Hashtable) context.getAttribute("idlist");
        HashMap hashMap = (HashMap) context.getAttribute("sessionid");
        Object objname = hashMap.get(sno);
        if (objname == null)
            return false;
        String useid = objname.toString();
        logger.info("hashtable长度 " + hashtable.size() + " hashMap的长度 " + hashMap.size());
        try {
            hashtable.remove(useid);
            hashMap.remove(sno);
        }catch (Exception e){
            logger.info("继续");
        }
        logger.info("Session 删除" + useid + " 项");
        hashtable = (Hashtable) context.getAttribute("idlist");
        logger.info("hashtable长度 " + hashtable.size() + " hashMap的长度 " + hashMap.size());
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return true;
    }
}
