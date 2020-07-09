package com.nio;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/11
 */

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

/**
 *@ClassName SocketClient
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/11 9:16
 *@Version 1.0
 **/
public class SocketClient {

    public static void main(String []args ){
        try {
            Socket s = new Socket("localhost",9090);
            Thread t = new Thread(() -> {
                try {
                    InputStream is = s.getInputStream();
                    byte [] b = new byte[1024];
                    int len = 0;
                    while((len = is.read(b,0,b.length)) > 0) {
                        System.out.println(new String (b,0,len));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(System.in));
            while(true) {
                String line = bufferedReader.readLine();
                OutputStream os = s.getOutputStream();
                byte[] bs = line.getBytes();
                os.write(bs);

            }

            //InputStream is = s.getInputStream();


            //s.getInputStream()
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
