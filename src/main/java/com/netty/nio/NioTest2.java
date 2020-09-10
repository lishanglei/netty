package com.netty.nio;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/4
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/4              lishanglei      v1.0.0           Created
 */
public class NioTest2 {
    public static void main(String[] args) throws Exception {

        FileInputStream fileInputStream =new FileInputStream("NioTest2.txt");
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer buffer =ByteBuffer.allocate(512);
        channel.read(buffer);
        buffer.flip();
        while(buffer.remaining()>0){
            byte b =buffer.get();
            System.out.println("character: "+(char)b);
        }
    }
}
