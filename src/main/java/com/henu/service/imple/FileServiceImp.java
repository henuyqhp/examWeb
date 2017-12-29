package com.henu.service.imple;

import com.google.common.collect.Lists;
import com.henu.dao.ExamMapper;
import com.henu.pojo.Exam;
import com.henu.service.FileService;
import com.henu.util.Const;
import com.henu.util.FTPUtil;
import com.henu.util.PageData;
import com.henu.util.enums.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@Service("fileService")
public class FileServiceImp implements FileService {
    Logger logger = LoggerFactory.getLogger(FileServiceImp.class);

    @Autowired
    private ExamMapper examMapper;
    /**
     * 文件上传
     * @param file 文件
     * @param path 路径
     * @return
     */
    @Override
    public String upload(MultipartFile file, String path,String remotePath) throws Exception {
        String fileName = file.getOriginalFilename();
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".")+1);
//         String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        String uploadFileName = fileName;

        logger.info("开始上传文件，上传的文件名:{}，上传的类型:{}，新文件名:{}",fileName,fileExtensionName,uploadFileName);

        //检验文件是否存在
        File fileDir = new File(path);
        if (!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }

        File targetFile = new File(path,uploadFileName);
        try {
            //文件上传成功
            file.transferTo(targetFile);
            //将文件上传到ftp服务器上
            FTPUtil.upLoadFile(Lists.newArrayList(targetFile),remotePath);
            //删除，upload文件夹下的文件
            targetFile.delete();

        } catch (IOException e) {
            logger.error("文件上传异常",e);
        }

        return targetFile.getName();
    }

    /**
     * 文件下载
     * @param name 文件完全限定名
     * @return
     * @throws Exception
     */
    @Override
    public PageData download(String name, String path, HttpServletResponse response) throws Exception {
        PageData pageData = new PageData();
        try{
            boolean result = FTPUtil.downLoadFile(name,path,response);
            if (result){
                pageData.put(Const.CODE, ResponseCode.成功.getCode());
                pageData.put("file",path + "/" + name);
            }else{
                pageData.put(Const.CODE,ResponseCode.错误.getCode());
                logger.error("文件下载失败");
            }
        }catch (Exception e){
            pageData.put(Const.CODE,ResponseCode.错误.getCode());
            logger.error("文件下载失败：{}",e);
        }
        return pageData;
    }

    @Override
    public PageData downZip(Integer eid,HttpServletResponse response) throws Exception {
        Exam exam = examMapper.selectByPrimaryKey(eid);
        System.out.println("查询到考试"+exam);
        FTPUtil.downZip(exam.getEname(),response);
        return null;
    }

    public static void main(String[] args) {
        try {
            new FileServiceImp().download("nginx.conf","D:",null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
