package com.henu.service.imple.teacher;

import com.henu.dao.ExamMapper;
import com.henu.dao.StudentMapper;
import com.henu.pojo.Exam;
import com.henu.pojo.Student;
import com.henu.service.teacher.OnExamService;
import com.henu.session.SessionSingleBean;
import com.henu.util.Const;
import com.henu.util.NumberUtil;
import com.henu.util.PageData;
import com.henu.util.enums.Enable;
import com.henu.util.enums.ExamStatus;
import com.henu.util.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

@Service("onExamService")
public class OnExamServiceImp implements OnExamService {

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageData lookExam(int eid) throws Exception{
        PageData pageData = new PageData();
        Exam exam = examMapper.selectByPrimaryKey(eid);
        if (exam == null){
            pageData.put(Const.CODE,ResponseCode.错误.getCode());
            return pageData;
        }
        pageData.put("exam",exam);
        pageData.put(Const.CODE,ResponseCode.成功.getCode());
        return pageData;
    }

    @Override
    public PageData lookExam(String ename) throws Exception {
        PageData pageData = new PageData();
        Exam exam = examMapper.selectByExamName(ename);
        if (exam == null){
            pageData.put(Const.CODE,ResponseCode.错误.getCode());
            return pageData;
        }
        pageData.put("exam",exam);
        pageData.put(Const.CODE,ResponseCode.成功.getCode());
        return pageData;
    }

    @Override
    public PageData unlock (HttpServletRequest request, String sno) throws Exception {
        PageData pageData = new PageData();
//        Student student = studentMapper.selectBySnoStudent(Integer.parseInt(sno));
        boolean isTrue = SessionSingleBean.getInstance().removeStudent(request,Integer.parseInt(sno));
        if (isTrue){
            pageData.put(Const.CODE,ResponseCode.成功.getCode());
            return pageData;
        }
        pageData.put(Const.CODE,ResponseCode.错误.getCode());
        return pageData;
    }
//    PageData pageData = new PageData();
//    Exam exam = examMapper.selectByExamName(ename);
//        if (exam == null){
//        pageData.put(Const.CODE, ResponseCode.错误.getCode());
//        return pageData;
//    }
//
//    Exam resetExam = new Exam();
//        resetExam.setId(exam.getId());
//        resetExam.setEenable(Enable.不可用.getCode());
//        resetExam.setStatus(ExamStatus.考试终止.getCode());
//    int count = examMapper.updateByPrimaryKeySelective(exam);
//        if (count > 0){
//        pageData.put(Const.CODE,ResponseCode.成功.getCode());
//        return pageData;
//    }
//        pageData.put(Const.CODE,ResponseCode.错误.getCode());
//        return pageData;
    @Override
    public PageData findStudent(String findkey) throws Exception {
        PageData pageData = new PageData();
        Student student = null;
        if (NumberUtil.isAllNumber(findkey)){
            student = studentMapper.selectBySnoStudent(Integer.parseInt(findkey));
        }else if(NumberUtil.isIP(findkey)){
            student = studentMapper.selectByIp(findkey);
        }else{
            student = studentMapper.selectBySname(findkey);
        }
        if (student != null){
            pageData.put(Const.CODE,ResponseCode.成功.getCode());
            pageData.put("student",student);
        }else{
            pageData.put(Const.CODE,ResponseCode.错误.getCode());
        }
        return pageData;
    }

    @Override
    public ResponseCode editStudent(PageData pd) throws Exception {
        pd.put("sno",pd.getInt("eno"));
        pd.put("classnum",pd.getString("eclassNum"));
        pd.put("sname",pd.getString("ename"));
        pd.put("ip",pd.getString("eip"));
        return  studentMapper.updateBySidAndPageData(pd) > 0 ? ResponseCode.成功 : ResponseCode.错误;
    }

    @Override
    public PageData examInformation(String ename) throws Exception {
        PageData pageData = new PageData();
        int sum = examMapper.canUseExamSum(ename);
        int login = examMapper.canUseExamLogin(ename);
        int submit = examMapper.canUseExamSubmit(ename);
        pageData.put("sum",sum);
        pageData.put("login",login);
        pageData.put("submit",submit);
        pageData.put("ename",ename);
        System.out.println(pageData);
        return pageData;
    }


    @Override
    public PageData manageStudent(PageData pd) throws Exception{
        int type = pd.getInt(Const.TYPE);
        PageData pageData = new PageData();
        switch (type){
            case 0:
                Student student = null;
                if (NumberUtil.isAllNumber(pd.getString("sid"))){
                    student = studentMapper.selectByPrimaryKey(pd.getInt("sid"));
                }else {
                    student = studentMapper.selectBySname(pd.getString("sid"));
                }

                if (student == null){
                    pageData.put(Const.CODE,ResponseCode.错误.getCode());
                    break;
                }
                student.setSpass("");
                pageData.put("student",student);
                break;
            case 1:
                int count = studentMapper.updateBySidAndPageData(pd);
                if (count > 0){
                    pageData.put(Const.CODE,ResponseCode.成功.getCode());
                    break;
                }
                pageData.put(Const.CODE,ResponseCode.错误.getCode());
                break;
            case 2:break;
        }
        return pageData;
    }
}
