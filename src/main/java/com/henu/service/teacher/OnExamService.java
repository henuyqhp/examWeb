package com.henu.service.teacher;

import com.henu.util.PageData;
import com.henu.util.enums.ResponseCode;

import javax.servlet.http.HttpServletRequest;


public interface OnExamService {


    PageData lookExam(int eid) throws Exception;

    PageData lookExam(String ename) throws Exception;

    PageData manageStudent(PageData pd) throws Exception;

    PageData unlock (HttpServletRequest request, String sno) throws Exception;

    PageData findStudent(String findkey) throws Exception;

    ResponseCode editStudent(PageData pd) throws Exception;

    PageData examInformation(String ename) throws Exception;
}
