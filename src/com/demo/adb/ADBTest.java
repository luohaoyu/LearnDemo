package com.demo.adb;

import com.utils.ShellCommand;
import org.apache.commons.exec.CommandLine;
import org.junit.Test;

/**
 * Created by Johnson on 2017/4/5.
 */
public class ADBTest {
    @Test
    public  void testExec(){
        ADB adb = new ADB("");
        adb.exec();
    }
    @Test
    public  void testExecAsync(){
        ShellCommand shellCommand = new ShellCommand();
        CommandLine commandLine = CommandLine.parse("adb deves");
        shellCommand.execSync(commandLine,6);
    }
}
