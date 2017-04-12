package com.test.slf4jtest;

import org.junit.Test;

/**
 * Created by Johnson on 2017/4/7.
 */
public class TestCase {
    @Test
    public void test1(){
        System.out.println(Thread.currentThread().getName());
    }
    @Test
    public void test2(){
        System.out.println(Thread.currentThread().getName());
    }
    @Test
    public void test3(){
        System.out.println(Thread.currentThread().getName());
    }
}
