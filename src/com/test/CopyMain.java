package com.test;

import com.test.slf4jtest.SuiteParallelTest;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

/**
 * Created by Johnson on 2017/4/7.
 */
public class CopyMain {
    public static void main(String[] args) {
        Result result=JUnitCore.runClasses(SuiteParallelTest.class);
        System.out.println("执行用例总数："+result.getRunCount()+";用例失败数："+result.getFailureCount());
//        List<String> devcies= new ArrayList();
//        devcies.add("1");
//        devcies.add("2");
//        devcies.add("3");
//        devcies.add("3");
//        devcies.add("3");
//        devcies.add("3");
//        devcies.add("3");
//        ExecutorService executorService = Executors.newFixedThreadPool(devcies.size());
//        for(String d:devcies) {
//            executorService.execute(() -> {
//                System.out.println(String.format("=====%s=======", Thread.currentThread().getName()));
//                JUnitCore.runClasses(CopyFileTest.class);
//            });
//        }
//        executorService.shutdown();
//        try {
//            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
////            e.printStackTrace();
//            Thread.currentThread().interrupt();
//        }

    }
}
