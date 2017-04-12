package com.test.proccess;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Johnson on 2017/3/31.
 */
public class DeviceStore {
    static final ReentrantLock lock = new ReentrantLock(true);
    final Condition condition=lock.newCondition();
    Random random=new Random(0);

    Deque<String > devices= new ArrayDeque<>();
    public String tack(){
//        System.out.println();
        try {
            lock.lockInterruptibly();
//            System.out.println(Thread.currentThread().getName()+"获取了锁");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String result=null;
        try {
            while (devices.isEmpty()) {
                System.out.println(Thread.currentThread().getName()+"take need wait ....");
                condition.await();
            }

            result=Thread.currentThread().getName()+"---tack.........."+devices.poll();
        }
        catch (InterruptedException e) {
                e.printStackTrace();
        }
        finally {
                lock.unlock();
        }

        return result==null?null:result;


    }
    public  void put(){
//        System.out.println(Thread.currentThread().getName()+"--put........"+obj);
        lock.lock();
        try {
            Thread.sleep(1000L);

            int data=random.nextInt(100);
            devices.offer(Integer.toString(data));
            System.out.println(Thread.currentThread().getName()+"--put........"+data+",通知等待线程");
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }



    }
    public static void productor(final DeviceStore deviceStore){

        ExecutorService executorService2 = Executors.newFixedThreadPool(5);
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            final int data = i;
            executorService2.submit(() -> deviceStore.put());
        }
        executorService2.shutdown();


    }
    public  Callable<Void> newComsumer(String name){
        return new Callable<Void>() {
            @Override
            public Void call() {
                Thread.currentThread().setName(name);
                String r=tack();
                System.out.println(Thread.currentThread().getName()+"获得了数据："+r);
                return null;
            }
        };
    }
    public  Callable<Void> newProductor(String name){
        return new Callable() {
            ReentrantLock lock = new ReentrantLock();
            @Override
            public Void call() {
                Thread.currentThread().setName(name);

//                System.out.println(Thread.currentThread().getName()+"制造了数据："+data);
                put();
                return null ;
            }
        };
    }
    public static void comsumer(final DeviceStore deviceStore){
                ExecutorService  executorService = Executors.newFixedThreadPool(5);
//                for(int i =0;i<10;i++) {
//                    System.out.println("启动"+(i+1)+"个消费者");
                    Future future=executorService.submit(() -> deviceStore.tack());
//                    Future future2=executorService.submit(() -> deviceStore.tack());
                    try {
                        String  data =(String )future.get();
                        System.out.println(data);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
//                }
                executorService.shutdown();
//                executorService.awaitTermination();
        System.out.println("comsumer end");

    }
    public static void main(String[] args) {
        final DeviceStore deviceStore =new DeviceStore();

//        comsumer(deviceStore);
//        productor(deviceStore);
        Set<Callable<Void>> pros=new HashSet<>();
        for(int i =1;i<20;i++)
            pros.add(deviceStore.newProductor("生产者"+i));

        Set<Callable<Void>> coms=new HashSet<>();
        for(int i =1;i<20;i++)
            coms.add(deviceStore.newComsumer("消费者"+i));

        ExecutorService  executorService =Executors.newFixedThreadPool(5);
        try {


            executorService.invokeAll(pros);
            executorService.invokeAll(coms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            executorService.shutdown();
        }


        System.out.println("end");

    }
}
