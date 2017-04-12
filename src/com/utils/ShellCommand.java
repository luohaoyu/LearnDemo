package com.utils;

import org.apache.commons.exec.*;
import org.apache.commons.exec.environment.EnvironmentUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Johnson on 2017/4/5.
 */
public class ShellCommand {
    private DefaultExecutor executor = new DefaultExecutor();
    private CommandLine commandLine=null;
    private PumpStreamHandler handler = null;
    private DefaultExecuteResultHandler resultHandler = null;


    public ShellCommand() {

    }

    /**
    * 执行命令
    *
    * */
    public void execSync(CommandLine commandLine,int timeoutInSecond){
        DefaultExecutor executor = new DefaultExecutor();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        executor.setWatchdog(new ExecuteWatchdog(timeoutInSecond*1000));
        executor.setStreamHandler(new PumpStreamHandler(bos));
        try {
            executor.execute(commandLine);
        }catch (ExecuteException e){
            System.out.println("执行命令行退出："+e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();

        }finally {
            System.out.println("执行结果是：");
            System.out.println(bos.toString());
            try {
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }

    public static void execAsync(String display,CommandLine commandline) throws IOException {

        DefaultExecutor exec = new DefaultExecutor();

        ExecuteResultHandler handler = new DefaultExecuteResultHandler();
        PumpStreamHandler streamHandler = new PumpStreamHandler(new PrintingLogOutputStream());
        exec.setStreamHandler(streamHandler);
        try {
            if (display == null || display.isEmpty()) {
                exec.execute(commandline, handler);
            } else {
                Map env = EnvironmentUtils.getProcEnvironment();
                EnvironmentUtils.addVariableToEnvironment(env, "DISPLAY=:" + display);

                exec.execute(commandline, env, handler);
            }
        } catch (Exception e) {

        }
    }

    public void execTimeout(){

    }

    private static class PrintingLogOutputStream extends LogOutputStream {

        private StringBuilder output = new StringBuilder();

        @Override
        protected void processLine(String line, int level) {
            System.out.println("OUTPUT FROM PROCESS: " + line);
            output.append(line).append("\n");
        }

        public String getOutput() {
            return output.toString();
        }
    }
}
