package com.henu.service.imple.teacher;

import com.henu.dao.ExamMapper;
import com.henu.dao.TeacherMapper;
import com.henu.pojo.Teacher;
import com.henu.service.teacher.AfterExamService;
import com.henu.util.Const;
import com.henu.util.PageData;
import com.henu.util.enums.ExamStatus;
import com.henu.util.enums.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("afterExamService")
public class AfterExamServiceImp implements AfterExamService {
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public PageData stopExam(int id) throws Exception{
        PageData pageData = new PageData();
        int result = examMapper.setExamStatus(ExamStatus.考试完成.getCode(),id);
        if (result > 0 ){
            pageData.put(Const.CODE, ResponseCode.成功.getCode());
        }else{
            pageData.put(Const.CODE,ResponseCode.错误.getCode());
        }
        return pageData;
    }

    @Override
    public ResponseCode reviseInformation(Integer id,String oldpass, String newpass) throws Exception {
        System.out.println("进入");
        Teacher teacher = teacherMapper.selectByPrimaryKey(id);
        System.out.println(teacher);
        if (teacher == null ){
            return ResponseCode.错误;
        }
        if (!teacher.getTpass().equals(oldpass)){
            return ResponseCode.错误;
        }

        Teacher newT = new Teacher();
        newT.setId(id);
        newT.setTpass(newpass);
        int count = teacherMapper.updateByPrimaryKeySelective(teacher);
        return count > 0 ? ResponseCode.成功 : ResponseCode.错误;
    }
}
