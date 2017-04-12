package com.test.proccess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Johnson on 2017/3/31.
 */
public class Command {
    public static void exec(String... args){
       ProcessBuilder pb = new ProcessBuilder(args);
        try {
            Process p=pb.start();
            BufferedReader br=new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            int index=1;
            while ((line=br.readLine())!=null){
                System.out.println(">>>>>>>>>"+index);
                System.out.print(line.trim());
//                if (line.endsWith("device")){
//                    String[] tmp=line.split("\\s");
//                    System.out.println("当前的设备序列号："+tmp[0]);
//                }
                index++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        exec("adb","devices");
    }
}
