package com.test;

import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * Created by Johnson on 2017/4/6.
 */
public class CopyFileTest {

    /**
     * 测试文件api
     * @throws Exception
     */
    @org.junit.Test
    public void copyFileApi() throws Exception {

//      copyFile("D:\\workspace\\testOK\\out\\production\\testOK\\Main.class","D:\\workspace\\testOK\\out\\artifacts\\test.class");
        File file = new File("abc/");
        System.out.println(file.isDirectory());
        file.mkdir();
        System.out.println(file.getAbsoluteFile().getPath());

    }

    /**
     * 测试文件拷贝有过滤条件
     */
    @org.junit.Test
    public void testCopyFileter(){
        try {
            CopyFile.copy("D:\\workspace\\testOK\\out\\artifacts", "D:\\workspace\\testOK\\out\\artifacts2\\", new FileFilter() {
                @Override
                public boolean accept(File pathname) {

                    if(pathname.isFile()&& pathname.getName().endsWith(".class")){
                        System.out.println("排除了文件>>>>>>>>"+ pathname.getName());
                        return false;
                    }
                    return true;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *测试文件拷贝没有过滤条件
     * @throws Exception
     */
    @Test
    public void copy() throws Exception {
        try {
            CopyFile.copy("D:\\workspace\\testOK\\out\\artifacts","D:\\workspace\\testOK\\out\\artifacts2\\",null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}