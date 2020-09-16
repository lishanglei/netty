package com.netty.proto.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * protoc --java_out=src/main/java src/protobuf/Person.proto
 * @author lishanglei
 * @version v1.0.0
 * @date 2020/9/2
 * @Description Modification History:
 * Date                 Author          Version          Description
 * ---------------------------------------------------------------------------------*
 * 2020/9/2              lishanglei      v1.0.0           Created
 */
public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {

        DataInfo.Student student = DataInfo.Student
                .newBuilder()
                .setName("张三")
                .setAge(20)
                .setAddress("青岛")
                .build();
        //序列化
        byte[] bytes =student.toByteArray();


        //反序列化
        DataInfo.Student student1 =DataInfo.Student.parseFrom(bytes);
        System.out.println(student1);
        System.out.println(student1.getName());
        System.out.println(student1.getAge());
        System.out.println(student1.getAddress());

    }
}
