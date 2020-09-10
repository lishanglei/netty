package com.netty.nio;


import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/10
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/10              lishanglei      v1.0.0           Created
 */
public class NioTest13 {
    /**
     * ASCII(American Standard Code for Information Interchange 美国信息交换标准代码)
     * 7bit表示一个字符,共计可以表示2^7(128)种字符
     *
     *
     * ISO-8859-1
     * 8bit表示一个字符,即一个字节(byte)来表示一个字符,共计可以表示2^8(256)个字符,向下完全兼容ASCII
     *
     * gb2312
     * 两个字节(byte)即16bit表示一个汉字
     *
     * gbk是对gb23123的延申
     *
     * gb18030是对gbk的延申,是对所有汉字兼容的国标编码
     *
     * big5 是台湾对繁体字中文的编码集
     *
     * unicode是对全球所有字符兼容的编码集
     * 采用两个字节来表示一个字符,共计2^16(65536)个字符
     *
     * 但是英文原本可以用一个字节表示,现在要使用两个字节表示(高8位填充0表示),纯英文存储空间翻倍
     *
     * UTF(Unicode Translation Format)
     * unicode是一种编码方式,而UTF则是一种存储方式;UTF-8是Unicode的实现方式之一
     *
     * UTF-16LE(little endian) 小端  0xFFFE
     * UTF-16BE(big endian) 大端  0xFEFF
     * 在文件开头Zero Width No-Break Space(一个0宽度不换行的空格)
     *
     * UTF-8字节表示形式
     * 英文:兼容ASCII码,一个字节表示
     * 汉字:三个字节表示
     * 最多可以用6个字节来表示一个字符
     *
     *
     * 2个16进制数 表示一个字节
     * 8个2进制数  表示一个字节
     *
     */

    public static void main(String[] args) throws IOException {


        String inputFile ="NioTest_input.txt";
        String outputFile ="NioTest_output.txt";

        RandomAccessFile inputRandomAccessFile =new RandomAccessFile(inputFile,"r");
        RandomAccessFile outputRandonAccessFile =new RandomAccessFile(outputFile,"rw");

        long inputLength =new File(inputFile).length();

        FileChannel  inputFileChannel =inputRandomAccessFile.getChannel();
        FileChannel outputFileChannel =outputRandonAccessFile.getChannel();

        //把文件内容全都加载到内存中
        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);

        System.out.println("=============================");
        Charset.availableCharsets().forEach((k,v)->{
            System.out.println(k+" , "+v);
        });

        System.out.println("=============================");

        Charset charset =Charset.forName("utf-8");
        CharsetEncoder encoder =charset.newEncoder();
        CharsetDecoder decoder =charset.newDecoder();

        //解码:将字节转换成字符或字符串
        CharBuffer charBuffer = decoder.decode(inputData);
        //编码:将字符或字符串转换成字节
        ByteBuffer outputData = encoder.encode(charBuffer);

        outputFileChannel.write(outputData);

        inputRandomAccessFile.close();
        outputRandonAccessFile.close();

    }


}
