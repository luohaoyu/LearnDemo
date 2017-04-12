package com.test.aa;

import java.io.File;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Johnson on 2017/3/3.
 */
public class DFS {
    public static void DEFTraverse(String path){
//        ArrayDeque<String> stack = new ArrayDeque<>();
//        stack.push("a");
//        stack.push("b");
//        stack.push("c");
//        System.out.println(stack.peek());
//        System.out.println(stack.peekFirst());
//        System.out.println(stack.peekLast());
//        System.out.println(stack.poll());
//        System.out.println(stack.pop());
//        System.out.println(stack.size());
        ArrayDeque<File> stack = new ArrayDeque<>();
        File root = new File(path);
        stack.push(root);
        while (!stack.isEmpty()){
            root = stack.pop();
            visit(root);
            File[] childs= root.listFiles();
            if(childs!=null){
                for(File child:childs){
                    stack.push(child);
                }
            }

        }

        }
    public static  void visit( File node){
        System.out.println(node.getPath());
    }
    public static void main(String[] args) {
        String file =DFS.class.getResource("/").getPath();
        DEFTraverse(file);
        Map<String,Boolean> node = new HashMap<>();
    }
}
