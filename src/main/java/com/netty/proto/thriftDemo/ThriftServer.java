package com.netty.proto.thriftDemo;

import com.netty.proto.generated.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/3
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/3              lishanglei      v1.0.0           Created
 */
public class ThriftServer {

    public static void main(String[] args) throws TTransportException {
        /**
         * thrift支持的服务模型
         * TSimpleServer 简单的单线程服务模型,常用于测试
         * TThreadPoolServer 多线程服务模型,使用标准的阻塞式IO
         * TNonblockingServer 多线程服务模型,用用非阻塞式IO(需使用TFramedTransport数据传输方式)
         * THsHaServer -THsHa 引入了线程池去处理,其模型把读写任务放到线程池去处理;Half-sync/Half-async的处理模式,Half-async是在处理IO时间上(accept/read/write io) Half-sync用于handler对rpc的同步处理
         */
        TNonblockingServerSocket socket =new TNonblockingServerSocket(8899);
        THsHaServer.Args arg =new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor =new PersonService.Processor<>(new PersonServiceImpl());
        /**
         * 数据协议/格式
         * TBinaryProtocol  二进制格式
         * TCompactProtocol 压缩格式
         * TJSONProtocol    json格式
         * TSimpleJSONProtocol 提供json只写协议,生成的文件很容易通过脚本语言解析
         * TDebugProtocol 使用易懂的可读的文本格式,以便于debug
         */
        arg.protocolFactory(new TCompactProtocol.Factory());
        /**
         * 数据传输方式
         * TSocket  阻塞式socket
         * TFramedTransport 以frame为单位进行传输,非阻塞式服务中使用
         * TFileTransport   以文件形式进行传输
         * TMemoryTransport 将内存用于I/O,java实现时内部实际使用了简单的ByteArrayOutPutStream
         * TZliTransport  使用zlib进行压缩,与其它传输方式联合使用,当前无java实现
         */
        arg.transportFactory(new TFastFramedTransport.Factory());
        arg.processorFactory(new TProcessorFactory(processor));

        TServer server =new THsHaServer(arg);
        System.out.println("thrift  server started!");
        server.serve();
    }
}
