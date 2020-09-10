package com.netty.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/7
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/7              lishanglei      v1.0.0           Created
 */
public class NioTest10 {

    public static void main(String[] args) throws IOException {

        RandomAccessFile randomAccessFile =new RandomAccessFile("NioTest10.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();
        FileLock lock = channel.lock(3,6,true);
        System.out.println("valid: "+lock.isValid());
        System.out.println("Lock Type: "+lock.isShared());
        lock.release();
        randomAccessFile.close();

    }
}
