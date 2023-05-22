package learnkotlin

//使用infix函数构建更加可读的语法
//抛开infix关键字除外
//这就是一个String的扩展函数,就是判断一个字符串是否是以某个指定参数靠头的
infix fun String.beginsWith(prefix: String) = startsWith(prefix)
//加了infix关键字后,我们就可以使用
fun main(){
    if("Hello kotlin" beginsWith "Hello")
        println("yes")
    else
        println("no")


    val list = listOf<String>("Apple", "Banana", "Orange", "Pear")
    if(list has "Banana")
        println("yesyes")
}
//就可以使用这个特殊的语法糖的格式进行调用函数
//抛弃了我们将函数调用时候的小数点,括号等计算机的相关语法
//从而使用一种更加接近英语的语法编写程序,让代码看起来更加有可读性

/*
infix函数由于其语法糖格式的特殊性，有两个比较严格的限制：首先，infix函数是
不能定义成顶层函数的，它必须是某个类的成员函数，可以使用扩展函数的方式将它定义到某
个类当中；其次，infix函数必须接收且只能接收一个参数，至于参数类型是没有限制的。只
有同时满足这两点，infix函数的语法糖才具备使用的条件
 */

//我们如果想要判断一个集合中是否包含某个指定的元素,使用infix关键字,我们可以这样写
infix fun <T> Collection<T>.has(element: T) = contains(element)


