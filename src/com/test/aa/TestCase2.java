package com.test.aa;

import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Johnson on 2017/2/28.
 */
public class TestCase2 implements TestBase{
    @Override
    public String run() {
        return "TestCase2";
    }
    @Test
    public void testSocket(){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0;i<10;i++){
            final int id=i;
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        ServerSocket serverSocket = new ServerSocket(0);
                        serverSocket.setReuseAddress(true);
                        System.out.println(">>>>>>>>>>>>>>>"+id);
                        System.out.println("当前线程id:"+ Thread.currentThread().getId());
                        System.out.println("Running test file: ");
                        System.out.println(serverSocket.getLocalPort());
//                    System.out.println(serverSocket.getLocalPort());
//                    System.out.println(serverSocket.getLocalPort());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executorService.shutdown();
        Stream stream=Stream.of("a","b","c");
        List<String> list= (List<String>) stream.map( (x)->((String)x).toUpperCase()).collect(Collectors.toList());
        System.out.println(list);
        List<String> list1=new ArrayList<>();
        list1.add("a");
        list1.add("c");
        list1.add("d");
        Stream<String> s=list1.stream();
        s.map(item->item.toUpperCase()).filter(item->item.toUpperCase().equals("A")).collect(Collectors.toList());
    }
}
