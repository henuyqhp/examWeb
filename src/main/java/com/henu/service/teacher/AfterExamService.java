package com.henu.service.teacher;

import com.henu.util.PageData;
import com.henu.util.enums.ResponseCode;
import com.henu.vo.User;

public interface AfterExamService {
    PageData stopExam(int id) throws Exception;

    ResponseCode reviseInformation(Integer id, String oldpass,String newpass) throws Exception;
}
