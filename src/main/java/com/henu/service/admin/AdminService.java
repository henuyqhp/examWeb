package com.henu.service.admin;


import com.henu.pojo.Exam;
import com.henu.pojo.Teacher;
import com.henu.util.PageData;

import java.util.List;

public interface AdminService {
    PageData login(PageData pd);
    //新建教师
    PageData newTeacher(PageData pd);
    //修改教师信息
    PageData ReviseTeacher(PageData pd);

    PageData DeletTeacher(PageData pd);

    PageData DeletExam(PageData pd);

    PageData SelectTeacher(PageData pd);

    List<Teacher> teacherBefore();

    List<Exam> examBefore();

    PageData findTeacher(int findTeacherkey);
}
