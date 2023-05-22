package learnkotlin

import java.lang.StringBuilder

class Money(val value: Int){
    //有趣的运算符函数
    operator fun plus(money: Money): Money{
        val sum = value + money.value
        return Money(sum)
    }

    //有趣的运算符重载函数
    operator fun plus(newValue: Int):Money{
        val sum = newValue + value
        return Money(sum)
    }

}

//有趣的运算符函数+函数扩展
operator fun String.times(n: Int): String{
    val builder = StringBuilder()
    //repeat(n)这个是循环里面的代码块n次
    repeat(n){
        builder.append(this)
    }
    return builder.toString()
}

fun main(){
    val money1 = Money(45)
    val money2 = Money(5)
    println((money1+5).value)

    val str = "abc" * 3
    println(str)
}
