package com.utils;

import org.junit.Test;

/**
 * Created by Johnson on 2017/4/5.
 */
public class TestParr {
    private Devices devices=Devices.getInstatnc();
    private AvailablePorts ap = new AvailablePorts();

    public TestParr(){
        System.out.println("执行构造器    public TestParr()");
    }
    @Test
    public void test1() throws InterruptedException {
//        System.out.println(Thread.currentThread().getName());
        try {
            System.out.println("=="+ap.getPort());
            System.out.println("=="+ap.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
        devices.take();
//        System.out.println("");
    }
    @Test
    public void test2() throws InterruptedException {
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(devices.take());
        try {
            System.out.println(ap.getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
        devices.take();
    }
    @Test
    public void test3() throws InterruptedException {
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(devices.take());
        try {
            System.out.println(new AvailablePorts().getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
        devices.take();
    }
    @Test
    public void test4() throws InterruptedException {
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(devices.take());
        try {
            System.out.println(new AvailablePorts().getPort());
        } catch (Exception e) {
            e.printStackTrace();
        }
        devices.take();
    }
}
