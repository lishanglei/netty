package com.netty.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/8
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/8              lishanglei      v1.0.0           Created
 */
public class NioClient {

    public static void main(String[] args) throws IOException {

        SocketChannel socketChannel =SocketChannel.open();
        socketChannel.configureBlocking(false);

        Selector selector =Selector.open();
        socketChannel.register(selector, SelectionKey.OP_CONNECT);
        socketChannel.connect(new InetSocketAddress("127.0.0.1",8899));
        while(true){

            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            for(SelectionKey selectionKey :selectionKeys){

                if(selectionKey.isConnectable()){
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    if(client.isConnectionPending()){
                        client.finishConnect();

                        ByteBuffer buffer =ByteBuffer.allocate(1024);
                        buffer.put((LocalDateTime.now()+"连接成功").getBytes());
                        buffer.flip();
                        client.write(buffer);

                        ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                        executorService.submit(()->{
                           while(true){

                               try {
                                   buffer.clear();
                                   InputStreamReader inputStreamReader =new InputStreamReader(System.in);
                                   BufferedReader bufferedReader =new BufferedReader(inputStreamReader);
                                   String sendMessage =bufferedReader.readLine();
                                   buffer.put(sendMessage.getBytes());
                                   buffer.flip();
                                   client.write(buffer);

                               } catch (Exception e) {
                                   e.printStackTrace();
                               }

                           }
                        });
                    }
                    client.register(selector,SelectionKey.OP_READ);
                }else if(selectionKey.isReadable()){
                    SocketChannel client = (SocketChannel) selectionKey.channel();
                    ByteBuffer buffer =ByteBuffer.allocate(1024);
                    int count =client.read(buffer);
                    if(count>0){
                        String receiveMessage =new String(buffer.array(),0,count);
                        System.out.println("receiveMessage :"+receiveMessage);
                    }
                }
            }
            selectionKeys.clear();
        }

    }
}
