package com.netty.nio;

import java.nio.ByteBuffer;

/**
 * 只读bytebuffer
 * asReadOnlyBuffer()方法会截取原有buffer生成一个新的buffer,但是原buffer和新buffer共享底层数组,两个buffer的position/limit/capacity各自独立
 * 只读buffer不能转化为读写buffer
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/7
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/7              lishanglei      v1.0.0           Created
 */
public class NioTest7 {

    public static void main(String[] args) {


        ByteBuffer buffer = ByteBuffer.allocate(10);

        System.out.println(buffer.getClass());
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte)i);
        }

        ByteBuffer readOnlyBuffer =buffer.asReadOnlyBuffer();
        System.out.println(readOnlyBuffer.getClass());
        readOnlyBuffer.position(0);
        readOnlyBuffer.put((byte)2);
    }
}
