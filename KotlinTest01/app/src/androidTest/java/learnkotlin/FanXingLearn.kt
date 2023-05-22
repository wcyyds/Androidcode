package learnkotlin

//定义一个泛型类
class MyClass<T>{
    fun method(param: T): T{
        return param
    }
}

//定义一个泛型方法
class Myclass1{
    fun <T> method(param: T): T{
        return param
    }
}

//我们现在使用泛型对build函数进行扩展,让他变成和apply函数实现的功能一模一样
//在build.kt文件里面编写

fun main(){
    //使用一个泛型类
    val myClass = MyClass<Int>()
    val result = myClass.method(123)
    println(result)

    //使用一个泛型方法
    val myClass1 = Myclass1()
    val result1 = myClass1.method<Int>(123)
    //因为kotlin有非常出色的类型推导机制,这里我们可以直接省略泛型的指定
    val result2 = myClass.method(123)


}