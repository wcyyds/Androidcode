package learnkotlin

//高阶函数的基本使用
//然后把他变成一个内联函数

//使用内联函数,我们使用的lambda表达式在底层被传唤成立匿名类的实现方式
//所以我们每次调用lambda表达式,都会创建一个新的匿名类,这会造成额外的内存和性能开销
//所以我们使用了内联函数的功能, 来消除lambda表达式带来的运行时的开销
//内联函数的关键字"inline"

inline fun num1AndNum2(num1: Int, num2: Int, operation: (Int, Int) -> Int): Int{
    val result = operation(num1, num2)
    return result
}

fun plus(num1: Int, num2: Int):Int{
    return num1 + num2
}

fun minus(num1: Int, num2: Int): Int{
    return num1 - num2
}

//使用高阶函数模仿一个apply函数的类似功能
//这里我们给StringBuilder类中定义了一个扩展函数,
//这个扩展函数接收了一个函数类型的参数,并且返回值也是StringBuilder
/*\
StringBuilder. 的语法结构。这是什么意思呢？其实这才是定义高阶函数完整的语
法规则，在函数类型的前面加上ClassName. 就表示这个函数类型是定义在哪个类当中的。
那么这里将函数类型定义到StringBuilder类当中有什么好处呢？好处就是当我们调用build
函数时传入的Lambda表达式将会自动拥有StringBuilder的上下文，同时这也是apply函数
的实现方式。
 */
fun StringBuilder.build(block: StringBuilder.() -> Unit): StringBuilder{
    block()
    return this
}


//内联函数和非内联函数的区别
//使用noinline对内联函数进行,取消内联
//这里展示的是内联函数引用的lambda表达式中是可以使用return关键字进行返回的,而非内联函数只能进行局部的返回
fun printString(str: String, block:(String) -> Unit){
    println("printString begin")
    block(str)
    println("printString end")
}

inline fun printString1(str: String, block:(String) -> Unit){
    println("printString begin111")
    block(str)
    println("printString end111")
}


fun main(){
    //高阶函数的应用
    val num1 = 100
    val num2 = 80
    val result1 = num1AndNum2(num1, num2, ::plus)
    val result2 = num1AndNum2(num1, num2, ::minus)
    println("result1 is $result1")
    println("result2 is $result2")

    //高阶函数的Lambda的写法来实现
    //使用lambda是最普遍的高阶函数调用方式
    val result3 = num1AndNum2(num1, num2){n1, n2 ->
        n1 + n2
    }
    val result4 = num1AndNum2(num1, num2){n1, n2 ->
        n1 -n2
    }
    println("result3 is $result3")
    println("result4 is $result4")

    //使用自己创建的高阶函数build函数来简化StringBuilder构建字符串
    val list = listOf("apple","banana","orange","pear","grape")
    val result = StringBuilder().build {
        append("Start eating fruit.\n")
        for(fruit in list){
            append(fruit).append("\n")
        }
        append("Ate all fruit.")
    }
    println(result.toString())

    //内联函数和非内联函数的区别
    println("main start")
    val str  = ""
    printString(str){ s ->
        println("lambda start")
        if(s.isEmpty())return@printString
        println(s)
        println("lambda end")
    }
    println("main end")

    //内联函数和非内联函数的区别
    println("main start111")
    val str1  = ""
    printString1(str1){ s ->
        println("lambda start111")
        if(s.isEmpty())return
        println(s)
        println("lambda end111")
    }
    println("main end111")

}