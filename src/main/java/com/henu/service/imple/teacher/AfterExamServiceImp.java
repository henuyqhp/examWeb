package com.henu.service.imple.teacher;

import com.henu.dao.ExamMapper;
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
}
