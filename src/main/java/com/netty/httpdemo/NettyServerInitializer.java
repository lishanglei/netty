package com.netty.httpdemo;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/1
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/1              lishanglei      v1.0.0           Created
 */
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {

        ChannelPipeline pipeline =channel.pipeline();
        //http编解码 HttpServerCodec=HttpRequestDecoder+HttpResponseEncoder
        pipeline.addLast("httpServerCodec",new HttpServerCodec());
        pipeline.addLast("nettyServerHandler",new NettyServerHandler());
    }
}
