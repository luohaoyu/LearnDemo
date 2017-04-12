package com.test.slf4jtest;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.Suite;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Johnson on 2017/4/7.
 */
public class SuiteParallel extends Suite {
    private final List<Description> descriptions = new ArrayList<>();
    private final ConcurrentHashMap<Runner, Description> suitDescriptions = new ConcurrentHashMap<Runner, Description>();
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    public SuiteParallel(Class<?> klass, RunnerBuilder builder) throws InitializationError {
        super(klass, builder);

    }

    public SuiteParallel(RunnerBuilder builder, Class<?>[] classes) throws InitializationError {
        super(builder, classes);
    }

    protected SuiteParallel(Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
        super(klass, suiteClasses);
    }

    protected SuiteParallel(RunnerBuilder builder, Class<?> klass, Class<?>[] suiteClasses) throws InitializationError {
        super(builder, klass, suiteClasses);
    }

    protected SuiteParallel(Class<?> klass, List<Runner> runners) throws InitializationError {
        super(klass, runners);
    }

//    @Override
//    protected List<Runner> getChildren() {
//        return ;
//    }

    @Override
    public Description getDescription() {
        Description description = Description.createSuiteDescription(Thread.currentThread().getName());
        for (Runner child : getChildren()) {
            System.out.println("\ngetDescription>>>>"+child.getDescription().getClassName());
            description.addChild(child.getDescription());
        }
        System.out.println(description.getClassName());
        return description;

    }

    @Override
    protected void runChild(final Runner runner, RunNotifier notifier) {
        System.out.println("=========runChild==========");
        System.out.println(runner.getDescription().getClassName());
        for (int i=0;i<5;i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    descriptions.add(Description.createSuiteDescription(Thread.currentThread().getName()));
                }
            });
        }
        for (Description d: descriptions) {
            System.out.println(d.getClassName());
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.runChild(runner,notifier);


    }

}
