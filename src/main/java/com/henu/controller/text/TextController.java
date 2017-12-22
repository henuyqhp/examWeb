package com.henu.controller.text;

import com.google.common.collect.Lists;
import com.henu.controller.base.BaseController;
import com.henu.util.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/text/")
public class TextController extends BaseController {

    @RequestMapping(value = "pd.do",method = RequestMethod.GET)
    @ResponseBody
    public PageData get(){
        System.out.println("开始get");
        PageData pd = new PageData();
        pd.put("map",request.getParameterMap());
        pd.put("dasd","dasd");
        List<String> aa = new ArrayList<>();
        aa.add("das");
        aa.add("2222");
        pd.put("list",aa);
        return pd;
    }

    @RequestMapping("one.do")
    public ModelAndView one(){
        ModelAndView mv = this.getModelAndView();
        PageData pd = this.getPageData();
        //前台传过来的数据
        System.out.println(pd);
        List list = Lists.newArrayList();
        if(pd.size() == 0){
            list.add("小缪那个");
            list.add("小dasdsa个");
            list.add("小缪达到");
            list.add("小缪D撒旦撒");
            list.add("D撒旦撒那个");
            pd.put("list",list);
        }

        //再次传到前台
        mv.addObject("pd",pd);

        mv.setViewName("one");
        return mv;
    }


}
