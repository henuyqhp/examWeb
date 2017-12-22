package com.henu.service.student;

import com.henu.pojo.LogExamSubit;
import com.henu.pojo.Student;
import com.henu.util.PageData;
import com.henu.vo.ExamVo;

import java.util.List;

public interface StudentService {
    PageData login(PageData pd);

    List<ExamVo> findCanUserExam(Integer userId);

    PageData insert(Integer eid,Integer sid,String filename);

    List<LogExamSubit> findLogExamSubit(Integer sid);
}
