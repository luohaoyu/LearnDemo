package com.demo.adb;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;

import java.io.IOException;

/**
 * Created by Johnson on 2017/4/5.
 */
public class ADB {
    private String path;

    public ADB(String path) {
        this.path = path;
    }

    public DefaultExecutor getExectuor() {
        DefaultExecutor executor = new DefaultExecutor();
        return executor;
    }

    public void exec() {
        CommandLine commandLine = CommandLine.parse("adb devices -l");
        DefaultExecutor executor =getExectuor();
        try {
            executor.execute(commandLine);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
