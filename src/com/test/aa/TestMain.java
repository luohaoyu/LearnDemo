package com.test.aa;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * Created by Johnson on 2017/2/28.
 */
public class TestMain {
    public static ArrayList<Class> getAllClassByInterface(Class clazz){
        ArrayList<Class<?>> list = new ArrayList<>();
        if (clazz.isInterface()){
//            for (int i = 0; i < ; i++) {
                
//            }
        }

        return null;
    }
    public static List<Class<TestBase>> getAllSubClassOfTestBase(){
        Field  field=null;
        Vector v=null;
        List<Class<TestBase>> allSubClass =  new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?> classOfClassLoader = classLoader.getClass();
        Class<TestBase> testBaseClass = null;
        try {
            testBaseClass=(Class<TestBase>) Class.forName("com.test.aa.TestBase");
//            testBaseClass

        } catch (ClassNotFoundException e) {
            new RuntimeException("无法找到TestBase的Class对象，请查看报名或者路径是否正确");
        }
        while (classOfClassLoader != ClassLoader.class) {
            classOfClassLoader = classOfClassLoader.getSuperclass();
        }

        return null;
    }
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        System.out.println(TestBase.class.getPackage().getName());
        System.out.println(TestCase1.class.isAssignableFrom(TestBase.class));
        System.out.println(TestBase.class.isAssignableFrom(TestCase1.class));
        Class<?> clazz=Thread.currentThread().
                getContextClassLoader().loadClass("com.test.aa.TestCase1");
        System.out.println(clazz.getComponentType());
        String[] arr = {"1","2","3"};
        String path= new TestMain().getClass().getResource("/").getPath();
        String path2= new TestMain().getClass().getResource("").getPath();
        String path3= new TestMain().getClass().getResource(".").getPath();
        System.out.println("user.dir-------------");
        System.out.println(System.getProperty("user.dir"));
        System.out.println(path);
        System.out.println(path2);
        System.out.println(path3);
        System.out.println("读取资源文件-------------");
        InputStream is =TestMain.class.getResourceAsStream("/res/test.txt") ;
       InputStreamReader isr= new InputStreamReader(is);
        ArrayList<String> list = new ArrayList<>();
       char[] bytes=new char[100];
        try {
            int num=0;
            while ((num=isr.read(bytes))!=-1){
                System.out.println(num);
                System.out.println(String.valueOf(bytes));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(arr.getClass().getComponentType());
        try {
            Constructor<TestCase1> c= (Constructor<TestCase1>) clazz.getConstructor(String.class);
            c.newInstance("hellow");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        String s="1";
        String s2="1";
        System.out.println(s==s2);
        System.out.println(s.hashCode()==s2.hashCode());
        String s3=new String("1");
        String s4=new String("1");
        System.out.println(s3==s4);
        System.out.println(s3.hashCode()==s4.hashCode());
        Object o3=new Object();
        Object o4=new Object();
        System.out.println(o3.hashCode()==o4.hashCode());
    }
//    URLClassLoader urlClassLoader =new URLClassLoader();


}
