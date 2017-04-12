package com.test;

import java.io.*;

/**
 * Created by Johnson on 2017/4/6.
 */
public class CopyFile {
    public static void copyFile(String src, String dest) {

        try {
            File destFile=new File(dest);
            if (!destFile.exists()) {//如果目标文件不存在创建文件
                destFile.createNewFile();
            }

            FileInputStream inputStream = new FileInputStream(src);
            FileOutputStream outputStream = new FileOutputStream(dest);

            byte[] buff = new byte[1024];
            int size;
            while ((size = inputStream.read(buff)) != -1) {
                outputStream.write(buff, 0, size);
            }
            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void copy(String src, String dest,final FileFilter fileFilter) throws IOException {
        File srcFile = new File(src);
        File destFile = new File(dest);
        //拼接新的目标文件的目录
        destFile=new File(destFile.getPath()+File.separator+srcFile.getName());
        if (srcFile.isDirectory()) {
            //如果源文件是个目录则在目标文件夹中创建相同的目录
            System.out.println("创建目录："+destFile.getPath());
            destFile.mkdirs();
            //遍历原文件目录下的文件
            File[] childFiles = (fileFilter==null)?srcFile.listFiles():srcFile.listFiles(fileFilter);
            for (File childFile : childFiles) {
                copy(childFile.getPath(),destFile.getPath(),fileFilter);//递归调用
            }

        } else {//如果源文件是个文件则直接拷贝
            System.out.println(String.format("拷贝文件%s 到 %s",srcFile.getPath(),destFile.getPath()));
            copyFile(srcFile.getPath(),destFile.getPath());
        }
    }


}
