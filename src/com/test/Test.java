package com.test;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test {

    private Lock lock = new ReentrantLock();

    public void insert(Thread thread ,AtomicInteger count) throws InterruptedException{
        lock.lockInterruptibly();   //注意，如果需要正确中断等待锁的线程，必须将获取锁放在外面，然后将InterruptedException抛出
        try {
            System.out.println(thread.getName()+"得到了锁,当前线程数量"+count.get());
            long startTime = System.currentTimeMillis();
            System.out.println(thread.getName()+"sleep "+1+"s");
            Thread.sleep(10*1000L);
        }
        finally {
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName()+"释放了锁");
        }
    }

    public static void main(String[] args)  {
        AtomicInteger threads = new AtomicInteger();
        Test test = new Test();
        MyThread thread1 = new MyThread(test,threads);
        MyThread thread2 = new MyThread(test,threads);
        thread1.start();
        thread2.start();
//        System.out.println("启动线程的数量："+MyThread.count.get());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("启动线程的数量："+threads.get());
        while (threads.get()>0){
            try {
                System.out.println(Thread.currentThread().getName()+"等待子线程执行完，未执行完的线程数"+threads.get());
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("所有子线程执行完毕！"+threads.get());
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        thread2.interrupt();

    }


}

class MyThread extends Thread {
    private Test test = null;
    private static  AtomicInteger count;
    public MyThread(Test test,AtomicInteger count) {
        this.test = test;
        this.count= count;
    }
    @Override
    public void run() {

        try {
            count.incrementAndGet();
            test.insert(Thread.currentThread(),count);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()+"被中断");
        }finally {
            count.decrementAndGet();
        }
    }
}