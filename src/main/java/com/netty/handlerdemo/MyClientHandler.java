package com.netty.handlerdemo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.time.LocalDateTime;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/1
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/1              lishanglei      v1.0.0           Created
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    protected void channelRead0(ChannelHandlerContext ctx, Long s) throws Exception {

        System.out.println("客户端收到: "+s);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        ctx.writeAndFlush(123456789L);
//        ctx.writeAndFlush(1L);
//        ctx.writeAndFlush(2L);
//        ctx.writeAndFlush(3L);
//        ctx.writeAndFlush(4L);
        //ctx.writeAndFlush(Unpooled.copiedBuffer("hello world", Charset.forName("utf-8")));

    }
}
