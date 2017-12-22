package com.henu.controller.base;

import com.henu.util.PageData;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.session = request.getSession();
        response.setHeader("Progma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setDateHeader("Expires",0);
    }

     public ModelAndView getModelAndView(){
        ModelAndView mv = new ModelAndView();
        return mv;
     }

    protected PageData getPageData(){
        return new PageData(this.request);
    }

    protected PageData getPageData(String param){
        String[] params = StringUtils.split(param,"&");
        PageData pd = new PageData();
        for (String string : params){
            String[] arr = StringUtils.split(string,"=");
            String key = arr[0];
            String value = "";
            if (arr.length == 2){
                value = arr[1];
            }
            pd.put(key,value);
        }
        return pd;
    }

    protected void forward(String url) throws ServletException, IOException {
        RequestDispatcher dispatcher = this.getRequest().getRequestDispatcher(url);
        dispatcher.forward(this.request, this.response);
    }


}
