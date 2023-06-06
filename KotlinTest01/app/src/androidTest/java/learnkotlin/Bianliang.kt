package learnkotlin

fun main() {
    val items = listOf("H", "e", "l", "l", "o")
    //通过索引来遍历List
    for (index in items.indices) {
        println("$index" + "${index}对应的值是：${items[index]}")
    }
    var name: String = "fds"

}
fun parser(any: Any): String =
    when(any){
        in 4..9 -> "in 4..9" //区间判断
        3 -> "value is 3"   //相等性判断
        2, 6 -> "value is 2 or 6"   //多值相等性判断
        is Int -> "is Int"   //类型判断
        else -> "else"       //如果以上条件都不满足，则执行 else
    }


