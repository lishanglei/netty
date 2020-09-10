package com.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/8
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/8              lishanglei      v1.0.0           Created
 */
public class NioServer {

    private static Map<String, SocketChannel> clientMap = new HashMap<>();


    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            try {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                selectionKeys.forEach(selectionKey -> {
                    final SocketChannel client;

                    if (selectionKey.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                        try {
                            client = server.accept();
                            client.configureBlocking(false);
                            client.register(selector, SelectionKey.OP_READ);
                            String key = "[" + UUID.randomUUID().toString() + "]";
                            clientMap.put(key, client);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    } else if (selectionKey.isReadable()) {

                        client = (SocketChannel) selectionKey.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        try {

                            int count = client.read(buffer);
                            if (count > 0) {
                                buffer.flip();
                                Charset charset = Charset.forName("utf-8");
                                String receiveMessage = String.valueOf(charset.decode(buffer).array());
                                System.out.println(client + ":" + receiveMessage);

                                String senderKey = "";
                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                    if (entry.getValue() == client) {
                                        senderKey = entry.getKey();
                                        break;
                                    }
                                }

                                for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {

                                    SocketChannel value = entry.getValue();
                                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                                    byteBuffer.put((senderKey + ":" + receiveMessage).getBytes());

                                    byteBuffer.flip();
                                    value.write(byteBuffer);
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });


                selectionKeys.clear();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
            }
        }

    }
}
