package com.utils;

import org.junit.Test;
import org.junit.experimental.ParallelComputer;
import org.junit.runner.JUnitCore;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Johnson on 2017/4/5.
 */
public class Runner {
    @Test
    public void test() throws InterruptedException {
        final AtomicInteger count= new AtomicInteger(2);

        Thread take=new Thread(()->{
//            System.out.println(Devices.getInstatnc());
            try {
                System.out.println("获取设备");
                System.out.println("...."+Devices.getInstatnc().take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread put=new Thread(()->{
//            System.out.println(Devices.getInstatnc());
//            System.out.println("添加设备");
            for (int i = 0; i < 10; i++) {
                Devices.getInstatnc().put(String.format("device[%s]",i));
            }
            System.out.println("完成");
        });
        put.start();
        Thread.sleep(1000L);
        take.start();


        take.join();
        put.join();
    }

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            Devices.getInstatnc().put(String.format("device[%s]",i));
        }
        System.out.println("完成");
        ParallelComputer computer= new ParallelComputer(false,true);
        JUnitCore.runClasses(computer,TestParr.class);
        System.out.println("==========");

    }
}
