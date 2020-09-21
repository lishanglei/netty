package com.netty.handlerdemo2;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/1
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/1              lishanglei      v1.0.0           Created
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast(new MyClientHandler());
    }

}
