package com.utils;

import org.apache.commons.exec.CommandLine;
import org.junit.Test;

/**
 * Created by Johnson on 2017/4/5.
 */
public class CommadPromptTest {
    @Test
    public void execSync() throws Exception {
        CommandLine commandLine = CommandLine.parse("ping www.baidu.com");
        new ShellCommand().execSync(commandLine,2);
    }

    @Test
    public void execAsync() throws Exception {
        CommandLine commandLine= CommandLine.parse("adb devcies");
        ShellCommand.execAsync(null,commandLine);
        ShellCommand.execAsync("hello",commandLine);
    }

    @Test
    public void execTimeout() throws Exception {

    }

}