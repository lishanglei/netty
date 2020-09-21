package com.netty.handlerdemo2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/1
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/1              lishanglei      v1.0.0           Created
 */
public class MyServerHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private  int count;

    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {


        byte[] buffer =new byte[msg.readableBytes()];
        msg.readBytes(buffer);
        String message =new String(buffer);
        System.out.println("服务端接收到的消息内容: " +message);
        System.out.println("服务端接收到的消息: " +(++count));

        ByteBuf response = Unpooled.copiedBuffer(UUID.randomUUID().toString(), Charset.forName("utf-8"));
        ctx.writeAndFlush(response);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        cause.printStackTrace();
        ctx.close();
    }


}
