package com.utils;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Johnson on 2017/4/5.
 */
public class Devices {
    static final ReentrantLock lock = new ReentrantLock();
    static final Condition empty= lock.newCondition();
    final LinkedList<String> devices = new LinkedList<>();
    private Devices(){
//        for (int i = 0; i < 10; i++) {
//            this.put(String.format("device[%s]",i));
//        }
    }
    public LinkedList<String> getDevices(){
        return devices;
    }
    private static class DevicesHoder{
          static final Devices instanc=new Devices();
    }

    public static Devices getInstatnc(){
        return DevicesHoder.instanc;
    }
    public void put(String device){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+"添加设备："+device);
            devices.add(device);
            empty.signalAll();
        }finally {
            lock.unlock();
        }

    }
    public String  take() throws InterruptedException{
//        System.out.println(Thread.currentThread().getName()+"获取了涉笔");
//        System.out.println(devices.isEmpty());
        lock.lockInterruptibly();

        try {
            while (devices.isEmpty()){
//                System.out.println(devices.isEmpty());
                System.out.println(Thread.currentThread().getName()+"正在等待,");
                empty.await();
            }
            System.out.println(Thread.currentThread().getName()+"获取了设备："+devices.peek());
            return devices.pop();

        }finally {
            lock.unlock();

        }
    }

 }
