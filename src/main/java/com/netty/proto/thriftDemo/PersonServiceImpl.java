package com.netty.proto.thriftDemo;

import com.netty.proto.generated.Person;
import com.netty.proto.generated.PersonService;
import org.apache.thrift.TException;

/**
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/3
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/3              lishanglei      v1.0.0           Created
 */
public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUserName(String userName) throws TException {
        System.out.println("userName:"+userName);
        Person person =new Person();
        person.setUserName(userName);
        person.setAge(14);
        person.setMarried(false);
        return person;
    }

    @Override
    public void savePerson(Person person) throws  TException {
        System.out.println("person:"+person);
        System.out.println(person.getUserName());
        System.out.println(person.getAge());
        System.out.println(person.isMarried());
    }
}
