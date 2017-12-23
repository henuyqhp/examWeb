package com.henu.service.imple.teacher;

import com.google.common.collect.Lists;
import com.henu.dao.BingdingMapper;
import com.henu.dao.ExamMapper;
import com.henu.dao.StudentMapper;
import com.henu.dao.TeacherMapper;
import com.henu.pojo.Bingding;
import com.henu.pojo.Exam;
import com.henu.pojo.Student;
import com.henu.pojo.Teacher;
import com.henu.service.teacher.BeforeExamService;
import com.henu.util.Const;
import com.henu.util.DateTimeUtil;
import com.henu.util.PageData;
import com.henu.util.PageInfo;
import com.henu.util.enums.Enable;
import com.henu.util.enums.ExamStatus;
import com.henu.util.enums.ResponseCode;
import com.henu.util.enums.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service("beforeExamService")
public class BeforeExamServiceImp implements BeforeExamService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private BingdingMapper bingdingMapper;

    @Override
    public PageData login(PageData pd) throws Exception{
        PageData pageData = new PageData();
        int count = teacherMapper.checkTeacherId(pd);
        if(count == 0)
        {
            pageData.put(Const.CODE, ResponseCode.错误.getCode());
            pageData.put(Const.MSG,"帐号错误");
            return pageData;
        }
        Teacher teacher = teacherMapper.selectLogin(pd);
        if (teacher == null){
            pageData.put(Const.CODE,ResponseCode.错误.getCode());
            pageData.put(Const.MSG,"密码错误");
            return pageData;
        }
        teacher.setTpass("");
        System.out.println(teacher);
        pageData.put(Const.USER_TYPE, UserType.教师.getCode());
        pageData.put(Const.USER, teacher);
        pageData.put(Const.CODE, ResponseCode.成功.getCode());
        return pageData;

    }

    @Override
    public ResponseCode newExam(PageData pd) throws Exception{
        System.out.println(4);
        Exam exam = new Exam();
        System.out.println(5);
        System.out.println(pd);
        exam.setEname(pd.getString("ename"));
        exam.setType(pd.getString("type"));
        exam.setEenable(Enable.可用.getCode());
        System.out.println(pd.getString("startTime") + "---start--");
        System.out.println(pd.getString("endTime") +  "---end--");
        exam.setStarttime(DateTimeUtil.strToDate2(pd.getString("startTime")));
        exam.setEndtime(DateTimeUtil.strToDate2(pd.getString("endTime")));
        exam.setEcreator(pd.getInt("tid"));
        exam.setStatus(ExamStatus.未开始.getCode());
        int count = examMapper.insert(exam);
        if (count > 0){
            return ResponseCode.成功;
        }
        return ResponseCode.错误;
    }

    @Override
    public PageData editExam(PageData pd)throws Exception {
        return null;
    }

    @Override
    public PageData submit(PageData pd) throws Exception{

        return null;
    }

    @Override
    public PageData addStudent(PageData pd) throws Exception{
        PageData pageData = new PageData();
        Student student = new Student();
        student.setSno(pd.getInt("sno"));
        student.setSname(pd.getString("sname"));
        student.setSpass(pd.getString("sno"));
        student.setEnable(Enable.可用.getCode());
        student.setClassnum(pd.getString("classNum"));
        int count = studentMapper.insert(student);
        Exam exam = examMapper.selectByExamName(pd.getString("ename"));
        int sid = studentMapper.selectBySno(pd.getInt("sno"));
        Bingding bingding = new Bingding();
        bingding.setEid(exam.getId());
        bingding.setSid(sid);
        int count2 = bingdingMapper.insert(bingding);
        if (count > 0 && count2 > 0){
            pageData.put(Const.CODE,ResponseCode.成功.getCode());
            return pageData;
        }
        pageData.put(Const.CODE,ResponseCode.错误.getCode());
        return pageData;
    }


    @Override
    public PageData startExam(PageData pd)throws Exception {
        PageData pageData = new PageData();
        Exam exam = examMapper.selectByPrimaryKey(pd.getInt("eid"));
        if (exam.getEenable() == Enable.可用.getCode() && exam.getStatus() != ExamStatus.考试完成.getCode()
                && DateTimeUtil.subtraction(exam.getEndtime())){
            pageData.put(Const.CODE,ResponseCode.错误.getCode());
            return pageData;
        }

        int result = examMapper.setExamStatus(ExamStatus.正在进行中.getCode(),exam.getId());
        if (result > 0){
            pageData.put(Const.CODE,ResponseCode.成功.getCode());
            return pageData;
        }
        pageData.put(Const.CODE,ResponseCode.错误.getCode());
        return pageData;
    }

    @Override
    public PageData updateExamFilePath(int id,String filepath) throws Exception {
        PageData pageData = new PageData();
        int result = examMapper.updateExamFilePath(id,filepath);
        if(result > 0 ){
            pageData.put(Const.CODE,ResponseCode.成功.getCode());
            return pageData;
        }
        pageData.put(Const.CODE,ResponseCode.错误.getCode());
        return pageData;
    }

    @Override
    public int readExcelStudent(List<Student> students) throws Exception {
        int count = 0;
        for (Student item : students){
            count += studentMapper.insert(item);
        }
        return count;
    }

    @Override
    public List<Exam> examList() throws Exception {
        List<Exam> exams = Lists.newArrayList();
        exams = examMapper.selectExamList();
        return exams;
    }

    @Override
    public ResponseCode bindExamAndStudent(String ename, List<Student> list) throws Exception {
        int eid = examMapper.selectByExamName(ename).getId();
        int count = bingdingMapper.insertList(eid,list);
        System.out.println(count  + "--------------------count");
        if (count >0){
            return ResponseCode.成功;
        }
        return ResponseCode.错误;
    }

    @Override
    public List<Exam> slecetByPage(int pageNum) throws Exception {
        int total = examMapper.selectCount();
        PageInfo pageInfo = new PageInfo(4,total,pageNum);
        System.out.println(pageInfo);
        List<Exam> list= Lists.newArrayList();
        list = examMapper.selectByPage(pageInfo.getStart(),pageInfo.getPageSize());
        return list;
    }

    @Override
    public PageInfo getPageInfor() throws Exception{
        int total = examMapper.selectCount();
        PageInfo pageInfo = new PageInfo(4,total,1);
        return pageInfo;
    }


}
