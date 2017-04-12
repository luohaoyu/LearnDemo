package com.test.slf4jtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Johnson on 2017/4/6.
 */
public class HelloWorld {
    private static final Logger logger= LoggerFactory.getLogger(HelloWorld.class);
    private  String outer="aaaa";
    private static String outer_static="static";
    private static String[] testClassPaths;
    public static String[] split(String string, String sep) {
        if ((string == null) || (string.length() == 0)) {
            return new String[0];
        }

        // TODO How different is this from:
        // return string.split(sep);

        int start = 0;
        int idx = string.indexOf(sep, start);
        int len = sep.length();
        List<String> strings = new ArrayList<>();

        while (idx != -1) {
            strings.add(string.substring(start, idx).trim());
            start = idx + len;
            idx = string.indexOf(sep, start);
        }

        strings.add(string.substring(start).trim());


        return strings.toArray(new String[strings.size()]);
    }
    private static String[] getTestClasspath() {
        if (null != testClassPaths) {
            return testClassPaths;
        }

        String testClasspath = System.getProperty("testng.test.classpath");
        if (null == testClasspath) {
            return null;
        }

        String[] classpathFragments= split(testClasspath, File.pathSeparator);
        testClassPaths = new String[classpathFragments.length];

        for(int i= 0; i < classpathFragments.length; i++)  {
            String path;
            if(classpathFragments[i].toLowerCase().endsWith(".jar") || classpathFragments[i].toLowerCase().endsWith(".zip")) {
                path= classpathFragments[i] + "!/";
            }
            else {
                if(classpathFragments[i].endsWith(File.separator)) {
                    path= classpathFragments[i];
                }
                else {
                    path= classpathFragments[i] + "/";
                }
            }

            testClassPaths[i]= path.replace('\\', '/');
        }

        return testClassPaths;
    }
    public static void main(String[] args) {
        for (String each: split("com\\test\\a",File.separator)) {
            logger.info(each);
        }
        logger.info(Integer.toString("coam.test.aa".indexOf("a",3)));
        logger.info("coam.test.aa".substring(1,3));
        logger.info(Boolean.toString("coam.test.aa.*".endsWith(".*")));
        logger.info(Integer.toString("coam.test.aa.*".lastIndexOf(".*")));
        String pacakageName= "coam.test.aa.*";
        logger.info(pacakageName.substring(0,pacakageName.lastIndexOf(".*")));
        logger.warn(String.valueOf(pacakageName.charAt(0)));

//        logger.info(getTestClasspath().toString());
//        Profiler profiler = new Profiler("测试哦");
//        Logger logger = LoggerFactory.getLogger(HelloWorld.class);
//        profiler.setLogger(logger);
//        profiler.start("logger.info");
//        logger.info("Hello World");
//        profiler.start("logger.debug");
//        logger.debug("Hello World");
//        profiler.start("logger.warn");
//        logger.warn("Hello World");
//        profiler.start("logger.erro");
//        logger.error("Hello World");
////        profiler.stop().print();
//        new Thread(()->{
//            logger.info("测试一下{}",HelloWorld.class.getResource("."));
//            logger.info("测试一下{}",HelloWorld.class.getResource("/"));}).start();
//        logger.info("测试一下{}",HelloWorld.class.getResource("."));
//        logger.info("测试一下{}",HelloWorld.class.getResource("/"));
//        logger.info("测试一下{}","a");
//        System.out.println(Thread.currentThread().getContextClassLoader());
//
//        Properties  properties = new Properties();
//        try {
//            properties.load(HelloWorld.class.getResourceAsStream("/rest.properties"));
//            properties.load(HelloWorld.class.getResourceAsStream("/ok/test.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        System.out.println( properties.getProperty("aa"));
//        System.out.println( properties.getProperty("bbb"));
        String packageName="com.test.aa";
//        packageName.lastIndexOf(".*");
        logger.info("packageName.lastIndexOf(\".*\")={}",packageName.lastIndexOf(".*"));
        String packageDirName=packageName.replace(".","/")+(packageName.length()>0?"/":"");
        logger.info("packageDirName={}",packageDirName);
        logger.info("packageDirName={}",packageDirName.substring(1));
        try {
            Enumeration<URL> dirEnumeration = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            while(dirEnumeration.hasMoreElements()){
                URL url=dirEnumeration.nextElement();
                logger.info(url.toString());
                logger.info(url.getProtocol());
                logger.info(url.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(HelloWorld.class.getResource("/"));
        System.out.println(HelloWorld.class.getResource("test"));
        System.out.println(HelloWorld.class.getResource("../test1"));
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("."));
        System.out.println(Thread.currentThread().getContextClassLoader().getResource("/"));
        System.out.println(Thread.currentThread().getContextClassLoader().getClass().getName());
        System.out.println(HelloWorld.class.getClassLoader().getClass().getName());
    }
}
