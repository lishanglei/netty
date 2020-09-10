package com.netty.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class NioTest3 {

    public static void main(String[] args) throws IOException {

        FileOutputStream fileOutputStream =new FileOutputStream("NioTest3.txt");

        FileChannel channel = fileOutputStream.getChannel();

        ByteBuffer buffer =ByteBuffer.allocate(512);
        byte[]  message ="hello world nio".getBytes();
        for(int i=0;i<message.length;i++){
            buffer.put(message[i]);
        }
        buffer.flip();
        channel.write(buffer);
        fileOutputStream.close();
    }
}
