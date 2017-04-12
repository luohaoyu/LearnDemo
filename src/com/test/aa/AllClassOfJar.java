package com.test.aa;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Stack;

/**
 * Created by Johnson on 2017/3/9.
 */
public class AllClassOfJar {
    private static  boolean isIn(File[] arry,File target){
        boolean has=false;
        for(int i=0;i<arry.length;i++){
            if(arry[i].compareTo(target)==0){
                has=true;
                break;
            }
        }
        return has;
    }
    public static void postTraversing(File root){
        Stack<File> stack = new Stack<>();
        File pre=null;
        stack.push(root);
        while (!stack.isEmpty()){
            root=stack.peek();
            if(root.isFile()||(pre!=null&& isIn(root.listFiles(),pre))){
                root=stack.pop();
                System.out.println(root.getPath());
                pre=root;
            }else if(root.isDirectory()){
                for(File child:root.listFiles()){
                    stack.push(child);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, URISyntaxException {
        String path=AllClassOfJar.class.getResource("/").getPath();
        System.out.println("path="+path);
        File root = new File(path);
        System.out.println("后序遍历-----------------------");
        postTraversing(root);
        System.out.println("后序遍历-----------------------");

//        String rootPath=root.getPath();
//        List<URL> classList= new ArrayList<>();
//        Stack<File> stack = new Stack<>();
//        if(root.isDirectory()){
//            stack.push(root);
//            while (!stack.isEmpty()){
//                root= stack.pop();
//                System.out.println(root.getPath());
//                if(root.getName().endsWith(".class")){
//                    try {
//                        classList.add(root.toURI().toURL());
//                    } catch (MalformedURLException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if(root.isDirectory()) {
//                    System.out.println("directory:"+root.getName());
//                    File[] files = root.listFiles();
//                    for (File file : files) {
//                        stack.push(file);
//                    }
//                }
//            }
//        }
//        System.out.println("-----------------------");
//        System.out.println(AllClassOfJar.class.getPackage().getName());
//        System.out.println(AllClassOfJar.class.getClassLoader());
//        System.out.println("----------------获取jar包中的所有类-----------------");
//        String jarPath="D:\\workspace\\apache-jmeter-3.0\\bin\\ApacheJMeter.jar";
//        File file = new File(jarPath);
//        JarFile jarFile = new JarFile(file);
//        Enumeration<JarEntry> enumeration= jarFile.entries();
//        for(;enumeration.hasMoreElements();){
//            JarEntry jarEntry = enumeration.nextElement();
//            String entryName=jarEntry.getName();
//            if(entryName.endsWith(".class")&&entryName.indexOf("$")==-1) {
//                System.out.println(jarEntry.getName().replace("/",".").replace(".class",""));
//                URL[] urls= new URL[]{new URL("file:/"+jarPath)};
//                System.out.println(urls[0].toURI().getPath());
//                URLClassLoader loader=new URLClassLoader(urls,Thread.currentThread().getContextClassLoader());
//                try {
//                    Class<?> clazz =loader.loadClass("org.apache.jmeter.NewDriver");
//                    System.out.println("包名字："+clazz.getPackage().getName());
//                    System.out.println("类名字："+clazz.getSimpleName());
//                    Method[] methods=clazz.getMethods();
//                    for(Method method:methods){
//                      if(method.getDeclaringClass().getName().equals("org.apache.jmeter.NewDriver")) {
////                        System.out.println(method.getDeclaringClass().getName().equals("org.apache.jmeter.NewDriver"));
//                          System.out.println("方法名字："+method.getName());
//                      }
//                    }
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        }


    }
}
