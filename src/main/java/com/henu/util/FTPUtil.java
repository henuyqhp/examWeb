package com.henu.util;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

/**
 * FTP服务器
 */
public class FTPUtil {

    private static Logger logger = LoggerFactory.getLogger(FTPUtil.class);


    private static String ftpIp = PropertiesUtil.getProperty("ftp.server.ip");

    private static String ftpUser = PropertiesUtil.getProperty("ftp.user");

    private static String ftpPass = PropertiesUtil.getProperty("ftp.pass");

    public FTPUtil(String ip, int port, String user, String pwd) {
        this.ip = ip;
        this.port = port;
        this.user = user;
        this.pwd = pwd;
    }

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }



    /**
     * 上传文件
     * @param fileList
     * @return
     */
    public static  boolean upLoadFile(List<File> fileList,String remotePath){
        FTPUtil ftpUtil = new FTPUtil(ftpIp,21,ftpUser,ftpPass);
        logger.info("开始链接FTP服务器");

        boolean result = ftpUtil.upLoadFile(remotePath,fileList);

        logger.info("结束上传，上传结果:{}",result);
        return result;
    }

    /**
     * 上传文件
     * @param remotePath 路径
     * @param fileList 文件
     * @return
     */
    private  boolean upLoadFile(String remotePath,List<File> fileList){
        boolean upload = true;
        FileInputStream fileInputStream = null;
        if (connectFTP(this.ip,this.port,this.user,this.pwd)){
            try {
                boolean a = ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for (File item : fileList) {
                    fileInputStream = new FileInputStream(item);
                    boolean up = ftpClient.storeFile(item.getName(),fileInputStream);
                    upload = up;
                    logger.info("上传是否成功:{}",upload);
                }
            } catch (IOException e) {
                logger.error("上传文件异常",e);
                upload = false;
            }finally {
                try {
                    fileInputStream.close();
                    ftpClient.disconnect();
                } catch (IOException e) {
                    logger.info("上传FTP文件流和FTP连接无法正常关闭");
                }
            }
        }
        return upload;
    }

    /**
     * 下载文件
     * @param name 文件名称
     * @param localpath 文件本地路径
     * @return
     */
    public static boolean downLoadFile(String name,String localpath,HttpServletResponse response){
        FTPUtil ftpUtil = new FTPUtil(ftpIp,21,ftpUser,ftpPass);
        logger.info("开始连接FTP服务器");
        boolean result = ftpUtil.downLoadFile(name,"pub/exam",localpath,response);

        logger.info("下载结束，下载结果:{}",result);
        return result;
    }

    /**
     * 下载文件
     * @param name 文件名称
     * @param remotepath 文件ftp服务器路径
     * @param localpath 文件本地路径
     * @return
     */
    public  boolean downLoadFile(String name, String remotepath, String localpath, HttpServletResponse response){
        int reply;
        if (connectFTP(this.ip,this.port,this.user,this.pwd)){
            logger.info("开始下载文件：{}",name);
            try {
                reply =  ftpClient.getReplyCode();
                if (!FTPReply.isPositiveCompletion(reply)){
                    ftpClient.disconnect();
                    logger.error("下载文件失败");
                    return false;
                }
                ftpClient.changeWorkingDirectory(remotepath);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                FTPFile[] ftpFiles = ftpClient.listFiles();
                for (FTPFile item : ftpFiles){
                    if (item.getName().equals(name)){
//                        File localFile = new File(localpath + "/" + item.getName());
//                        OutputStream os = new FileOutputStream(localFile);
//                        ftpClient.retrieveFile(item.getName(),os);
                        InputStream in = ftpClient.retrieveFileStream(item.getName());
                        response.reset();
                        response.setContentType("application/octet-stream;charset=utf-8");
                        response.setHeader("Content-disposition","attachment;filename="+ URLEncoder.encode(item.getName(),"utf-8"));
                        response.setHeader("Accept-Ranges","bytes");
                        response.addHeader("Content-Length", String.valueOf(item.getSize()));
                        ServletOutputStream out = response.getOutputStream();
                        BufferedOutputStream outputStream = new BufferedOutputStream(out);
                        byte[] b = new byte[1024];
                        int i;
                        while ((i=in.read(b))>0){
                            outputStream.write(b,0,i);
                        }
                        outputStream.flush();
                        outputStream.close();
//                        os.close();
                        out.close();
//                        logger.info("文件下载到本地:{}",localFile.getAbsoluteFile());
                        logger.info("文件下载成功");
                        return true;
                    }
                }
                logger.info("FTP服务器没有此文件");
            } catch (IOException e) {
                logger.error("文件下载错误：{}",e);
            }
        }
        return false;
    }
    /**
     * 连接FTP服务器
     * @param ip ip地址
     * @param port 端口号
     * @param user 用户
     * @param password 密码
     * @return 是否成功连接
     */
    private  boolean connectFTP(String ip,int port,String user,String password){
        boolean isSuccess = false;
        ftpClient = new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess = ftpClient.login(user,password);
        } catch (IOException e) {
            logger.error("连接FTP服务器异常",e);
        }
        return isSuccess;
    }
}
