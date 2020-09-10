package com.netty.thriftDemo;

import com.netty.thrift.generated.Person;
import com.netty.thrift.generated.PersonService;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/3
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/3              lishanglei      v1.0.0           Created
 */
public class ThriftClient {

    public static void main(String[] args) {

        TTransport transport = new TFastFramedTransport(new TSocket("localhost", 8899), 600);
        TProtocol protocol = new TCompactProtocol(transport);
        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();
            Person person = client.getPersonByUserName("张三");
            System.out.println(person.getUserName());

            Person p =new Person();
            p.setUserName("李四");
            p.setAge(14);
            p.setMarried(true);
            client.savePerson(p);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }

    }
}
