package com.netty.httpdemo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/1
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/1              lishanglei      v1.0.0           Created
 */
public class NettyServer {


    public static void main(String[] args)  {

        //事件循环组
        //boss从客户端接收连接,但是不做处理,而是交给worker进行实际业务处理
        //一些参数的初始化赋值
        EventLoopGroup bossGroup =new NioEventLoopGroup();
        EventLoopGroup workerGroup =new NioEventLoopGroup();

        try {
            //服务启动器
            ServerBootstrap serverBootstrap =new ServerBootstrap();


            serverBootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new NettyServerInitializer());    //子处理器,我们自己定义的实际业务处理器

            ChannelFuture channelFuture = serverBootstrap.bind(8899).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
