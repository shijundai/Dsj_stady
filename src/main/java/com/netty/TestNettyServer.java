package com.netty;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/3
 */

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 *@ClassName TestNettyServer
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/3 16:09
 *@Version 1.0
 **/
public class TestNettyServer {

    public static void main(String []args) throws InterruptedException {
        NioEventLoopGroup loopGroup = new NioEventLoopGroup(1);
//        ChannelInboundHandlerAdapter clientHandler = new ChannelInboundHandlerAdapter() {
//            @Override
//            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//                ByteBuf bf = (ByteBuf) msg;
//                System.out.println(bf.getCharSequence(0,bf.readableBytes(), Charset.forName("UTF-8")));
//                Channel sockechannel = ctx.channel();
//                sockechannel.writeAndFlush(bf);
//            }
//        };
        NioServerSocketChannel server = new NioServerSocketChannel();
        loopGroup.register(server);
        server.bind(new InetSocketAddress(9090)).sync();
        server.pipeline().addLast(new ChannelInboundHandlerAdapter() {
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                SocketChannel socketChannel = (SocketChannel) msg;
                loopGroup.register(socketChannel);
                socketChannel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf bf = (ByteBuf) msg;
                        System.out.println(bf.getCharSequence(0,bf.readableBytes(), Charset.forName("UTF-8")));
                        Channel sockechannel = ctx.channel();
                        sockechannel.writeAndFlush(bf);
                    }
                });


                System.out.println(this.hashCode());
                System.out.println(Thread.currentThread().getName());
                System.out.println(msg);
            }

        });
    }

}

