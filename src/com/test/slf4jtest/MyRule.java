package com.test.slf4jtest;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Created by Johnson on 2017/4/7.
 */
public class MyRule implements TestRule{
    @Override
    public Statement apply(final Statement base,final Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                if (description.isSuite()){
                    System.out.println("当前执行的是Suit");
                }
                base.evaluate();
            }
        };
    }
}
