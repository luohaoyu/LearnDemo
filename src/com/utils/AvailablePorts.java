package com.utils;

import java.net.ServerSocket;

/**
 * Created by Johnson on 2017/4/5.
 */
public class AvailablePorts {
    /*
    * Generates Random ports
    * Used during starting appium server
    */
    public int getPort() throws Exception {
        ServerSocket socket = new ServerSocket(0);
        socket.setReuseAddress(true);
        int port = socket.getLocalPort();
        socket.close();
        return port;
    }
}
