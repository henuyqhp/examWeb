package com.henu.util;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipUtil {
    /**
     * 功能：把 sourceDir 目录下的所有文件进行 zip 格式的压缩，保存为指定 zip 文件
     *
     * @param sourceDir
     *            如果是目录，eg：D:\\MyEclipse\\first\\testFile，则压缩目录下所有文件；
     *            如果是文件，eg：D:\\MyEclipse\\first\\testFile\\aa.zip，则只压缩本文件
     * @param zipFile
     *            最后压缩的文件路径和名称,eg:D:\\MyEclipse\\first\\testFile\\aa.zip
     */
    public static File doZip(String sourceDir, String zipFilePath)
            throws IOException {
        File file = new File(sourceDir);
        File zipFile = new File(zipFilePath);
        ZipOutputStream zos = null;
        try {
            // 创建写出流操作
            OutputStream os = new FileOutputStream(zipFile);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            zos = new ZipOutputStream(bos);

            // 获取目录

            String basePath = null;
            if (file.isDirectory()) {
                basePath = file.getPath();
            } else {
                basePath = file.getParent();
            }
            zipFile(file, basePath, zos);
        } finally {
            if (zos != null) {
                zos.closeEntry();
                zos.close();
            }
        }
        return zipFile;
    }

    /**
     * @param source
     *            源文件
     * @param basePath
     * @param zos
     */
    private static void zipFile(File source, String basePath,
                                ZipOutputStream zos) throws IOException {
        File[] files = null;
        if (source.isDirectory()) {
            files = source.listFiles();
        } else {
            files = new File[1];
            files[0] = source;
        }

        InputStream is = null;
        String pathName;
        byte[] buf = new byte[1024];
        int length = 0;
        try {
            for (File file : files) {
                if (file.isDirectory()) {
                    pathName = file.getPath().substring(basePath.length() + 1) + "/";
                    zos.putNextEntry(new ZipEntry(pathName));
                    zipFile(file, basePath, zos);
                } else {
                    pathName = file.getPath().substring(basePath.length() + 1);
                    is = new FileInputStream(file);
                    BufferedInputStream bis = new BufferedInputStream(is);
                    zos.putNextEntry(new ZipEntry(pathName));
                    while ((length = bis.read(buf)) > 0) {
                        zos.write(buf, 0, length);
                    }
                    bis.close();
                }
            }
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}