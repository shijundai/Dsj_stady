package com.nio;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/6/11
 */

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *@ClassName SocketServer
 *@Description TODO
 *@Author Administrator
 *@Date 2020/6/11 8:45
 *@Version 1.0
 **/
public class SocketServer {

    /**
     * IO 网络IO
     * @param args
     */
    public static void main(String []args) {
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(9090));
            serverSocketChannel.configureBlocking(false);
            List<SocketChannel> socketChannelList = new ArrayList<>();
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while(true) {
                while(selector.select() > 0) {
                    Set<SelectionKey> keys = selector.selectedKeys();
                    Iterator<SelectionKey> iter = keys.iterator();
                    while (iter.hasNext()) {
                        SelectionKey selectKey = iter.next();
                        iter.remove();
                        if(selectKey.isAcceptable()) {
                            ServerSocketChannel channel = (ServerSocketChannel)selectKey.channel();
                            SocketChannel sockectChannel = channel.accept();
                            sockectChannel.configureBlocking(false);
                            sockectChannel.register(selector,SelectionKey.OP_READ);
                            System.out.println("得到一个连接");
                        } else if(selectKey.isReadable()) {
                            //读取
                            System.out.println("有数据进来:");
                            SocketChannel socketChannel = (SocketChannel)selectKey.channel();
                            ByteBuffer bf = ByteBuffer.allocate(128);
                            long len = 0;
                            while ((len = socketChannel.read(bf)) != 0) {
                                System.out.println("socketChannel.read "+len);
                                if(len > 0) {
                                    System.out.println("读取之前bf: " + bf);
//                                    bf.rewind();
                                    System.out.println("读取之前rewind: " + bf);

                                    bf.flip();
//                                    System.out.println("读取之前flip: " + bf);
                                    byte []b = new byte[bf.limit()-2];
                                    bf.get(b);
                                    System.out.println("读取之后: " + bf);
                                    System.out.println(new String(b));
                                    bf.compact();
                                    System.out.println("读取之后compact: " + bf);
                                    bf.put("Hello!".getBytes());
                                    bf.flip();
//                                    bf.compact();
//                                    System.out.println("读取之后compact: " + bf);
//                                    bf.put("Hello!".getBytes());
//                                    System.out.println("写入之后: " + bf);
//                                    bf.flip();
//                                    System.out.println("写入之后flip: " + bf);
//                                    System.out.println("发送...");

                                    socketChannel.write(bf);
                                    bf.compact();

                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
