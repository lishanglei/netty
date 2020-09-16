package com.netty.proto.protoDemo;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/3
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/3              lishanglei      v1.0.0           Created
 */
public class TestHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage msg) throws Exception {

        MyDataInfo.MyMessage.DataType dataType =msg.getDataType();
        System.out.println("dataType:"+dataType);
        if(dataType==MyDataInfo.MyMessage.DataType.PersonType){
            MyDataInfo.Person person = msg.getPerson();
            System.out.println(person.getAge());
            System.out.println(person.getName());
            System.out.println(person.getAddress());
        }else if(dataType==MyDataInfo.MyMessage.DataType.DogType){
            MyDataInfo.Dog dog = msg.getDog();
            System.out.println(dog.getAge());
            System.out.println(dog.getName());

        }else if(dataType==MyDataInfo.MyMessage.DataType.CatType){
            MyDataInfo.Cat cat = msg.getCat();
            System.out.println(cat.getCity());
            System.out.println(cat.getName());

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
