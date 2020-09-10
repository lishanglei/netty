namespace java thrift.generated
namespace py py.thrift.generated

//编译命令  thrift --gen java src/thrift/data.thrift
//为数据类型起别名
typedef i16 short
typedef i32 int
typedef i64 long
typedef bool boolean
typedef string String

struct Person{
    1:optional String userName,
    2:optional int age,
    3:optional boolean married
}
exception DataException{
    1:optional String message,
    2:optional String callStack,
    3:optional String date
}

service PersonService{

    Person getPersonByUserName(1:required String userName) throws (1:DataException dataException),
    void savePerson(1:required Person person) throws(1:DataException dataException)

}