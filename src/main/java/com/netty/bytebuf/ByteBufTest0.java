package com.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/18
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/18              lishanglei      v1.0.0           Created
 */
public class ByteBufTest0 {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.buffer(10);
        for (int i = 0; i < 10; i++) {
            byteBuf.writeByte(i);
        }
//
//        for(int i=0;i<byteBuf.capacity();i++){
//            System.out.println(byteBuf.getByte(i));
//        }


        for(int i=0;i<byteBuf.capacity();i++){
            System.out.println(byteBuf.readByte());
        }
    }
}
