package com.test.aa;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by Johnson on 2017/2/22.
 */
public class MySocketServer {
    public static void main(String[] args) throws InterruptedException {
        try {
            Selector selector =Selector.open();
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            ServerSocket serverSocket=serverSocketChannel.socket();
            serverSocket.bind(new InetSocketAddress(1000));
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
//            SocketChannel socketChannel = null;
            System.out.println("服务器运行...");
            while (true){
                Thread.sleep(1000);
                System.out.println(selector.select());
                if(selector.select()>0){
                    Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();
                        iterator.remove();
                        if ( (selectionKey.readyOps() & SelectionKey.OP_ACCEPT) ==
                                SelectionKey.OP_ACCEPT) {
                            ServerSocketChannel ssc = (ServerSocketChannel) selectionKey.channel();
                            SocketChannel sc = ssc.accept();
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                            try {
                                while (sc.read(buffer) != -1) {
                                    buffer.flip();
                                    System.out.println("收到请求数据：" + new String(buffer.array()));
                                    sc.write(buffer);
                                    buffer.clear();
                                }

                            } catch (IOException e) {
                                System.out.println("服务器异常");
                                continue;

                            } finally {
                                sc.close();
                                ssc.close();
                            }
                        }
                    }

                }else {
                    System.out.println("没有一个已注册的事件发生");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
