package com.test.aa;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

public class Main {
    public  static void readFile(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File(path));
        FileChannel fc = fileInputStream.getChannel();
//        fc =fc.truncate(10);
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (fc.read(buffer)!=-1){
//            System.out.println(new String(buffer.array()));
            buffer.flip();
            System.out.println(new String(buffer.array()));
            fc.write(buffer);
            buffer.clear();
//            System.out.println(buffer.limit()+","+buffer.position());
        }
        fc.close();
        fileInputStream.close();
    }
    @Test(timeout = 10000)
    public void testSocketChannel() throws IOException {
//        SocketChannel socketChannel = SocketChannel.open();
//        socketChannel.connect(new InetSocketAddress("baidu.com",80));
//        socketChannel.finishConnect();
        Socket s = new Socket("localhost", 1000);
        ReadableByteChannel socketChannel = Channels.newChannel(s.getInputStream());
        WritableByteChannel wChannel = Channels.newChannel(s.getOutputStream());
        if(s.isConnected()) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            byteBuffer.put("你好哦".getBytes());
            byteBuffer.flip();
            wChannel.write(byteBuffer);
            byteBuffer.clear();
            while (socketChannel.read(byteBuffer)!=-1){
                byteBuffer.flip();
                System.out.println(new String(byteBuffer.array()));
                byteBuffer.clear();

            }
        }else {
            System.out.println("链接句");
        }
    }

    public static void main(String[] args) throws IOException {
//        System.out.println(System.getProperty("user.dir"));
//        System.out.println(new Main().getClass().getResource(""));
//
//        FileInputStream fis = null;
//        FileOutputStream fos =null;
//        ByteBuffer byteBuffer=null;
//        try {
//            fis=new FileInputStream(new File("D:\\workspace\\testOK\\out\\test.bat"));
//            fos = new FileOutputStream(new File("D:\\workspace\\testOK\\out\\test-copy.text"));
//            FileChannel fic = fis.getChannel();
//            FileChannel foc = fos.getChannel();
//            byteBuffer= ByteBuffer.allocate(20);
//            fic=fic.truncate(10);
//            fic.read(byteBuffer);
//            byteBuffer.flip();
//
//            System.out.println(foc.size());
//            foc.write(byteBuffer);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        readFile("D:\\\\workspace\\\\testOK\\\\out\\\\test.bat");
//        testSocketChannel();
//        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
//        byteBuffer.put();
//        String a=new String (Charset.forName("UTF-8").decode(byteBuffer).array());
//        System.out.println(">>>>"+a);
        System.out.println(ClassLoader.getSystemClassLoader().getResource("com/test/aa"));
        System.out.println(ClassLoader.getSystemClassLoader().getResource(""));
        System.out.println(new Main().getClass().getResource(""));
        System.out.println(new Main().getClass().getResource("/com/test/aa/DFS.class"));
        System.out.println(Main.class.getResource(""));
        System.out.println(Main.class.getResource("/"));
        URL url = new Main().getClass().getResource("/com/test/aa/DFS.class");
        File file = new File(url.getFile());
        System.out.println(file.getName());
        System.out.println(file.getPath());
        System.out.println(file.isDirectory());
        System.out.println(file.isFile());

    }
}
