package com.henu.service;

import com.henu.util.PageData;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {

    String upload(MultipartFile file,String path,String remotePath) throws Exception;

    PageData download(String name, String path, HttpServletResponse response) throws Exception;
}
