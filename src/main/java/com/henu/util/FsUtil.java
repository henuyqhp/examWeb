package com.henu.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FsUtil {
    public static void delTree(File dir) {
        if (dir.exists()) {
            if (dir.isDirectory()) {
                for (File f : dir.listFiles())
                    delTree(f);
            }
            dir.delete();
        }
    }

    public static void mkdir(String dirPath) {
        File fp = new File(dirPath);
        if (!fp.exists()) {
            fp.mkdirs();
        }
    }

    public static boolean move(String srcFile, String destPath) {
        File src = new File(srcFile);
        if (!src.exists()) {
            return false;
        }
        File dir = new File(destPath);
        if (!dir.exists()) {
            return false;
        }
        return src.renameTo(new File(dir, src.getName()));
    }

    public static boolean move(String srcFile, String destPath, String destFile) {
        File src = new File(srcFile);
        if (!src.exists()) {
            return false;
        }
        File dir = new File(destPath);
        if (!dir.exists()) {
            return false;
        }
        return src.renameTo(new File(dir, destFile));
    }

    public static List<String[]> listDir(File dir) {
        List<String[]> dirlist = new ArrayList<>();
        if (dir.exists()) {
            File[] files = dir.listFiles();
            for (File f : files) {
                dirlist.add(new String[] { f.getName(), f.length() + "B",
                        (new Date(f.lastModified())).toString() });
            }
        }
        return dirlist;
    }
}