package com.test.aa;

/**
 * Created by Johnson on 2017/2/28.
 */
public class TestCase1 implements TestBase{
    static {
        System.out.println("hello world!");
    }
    public TestCase1(String a){
        System.out.println("new ");
    }
    @Override
    public String run() {
        return "这是TestCase1";
    }
}
