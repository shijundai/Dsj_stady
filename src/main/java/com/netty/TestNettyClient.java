package com.netty;/**
 * @author Administrator
 * @Title: Controller
 * @Description:
 * @date 2020/7/3
 */

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 *@ClassName TestNettyClient
 *@Description TODO
 *@Author Administrator
 *@Date 2020/7/3 11:55
 *@Version 1.0
 **/
public class TestNettyClient {

    public static void main(String []args) throws InterruptedException {
        NioEventLoopGroup nioEventLoop = new NioEventLoopGroup(1);
        NioSocketChannel nsc = new NioSocketChannel();
        nsc.pipeline().addLast(new ChannelInboundHandlerAdapter(){
            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf bf = (ByteBuf) msg;
                System.out.println(bf.getCharSequence(0,bf.readableBytes(), Charset.forName("UTF-8")));
                Channel sockechannel = ctx.channel();
                sockechannel.writeAndFlush(bf);
            }
        });
        nioEventLoop.register(nsc);
        ChannelFuture connect = nsc.connect(new InetSocketAddress("192.168.40.206", 9090));
        ByteBuf buf = Unpooled.copiedBuffer("Hello Server".getBytes());
        ChannelFuture send = nsc.writeAndFlush(buf);

//        ChannelFuture consync = connect.sync();
//        send.sync();
//        consync.channel().closeFuture().sync();
    }
}
