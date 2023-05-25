package learnkotlin

import java.lang.RuntimeException

//fun max(vararg nums: Int): Int{
//    var maxNum = Int.MIN_VALUE
//    for(num in nums){
//        maxNum = kotlin.math.max(maxNum, num)
//    }
//    return maxNum
//}

fun <T : Comparable<T>> max(vararg nums: T): T{
    if(nums.isEmpty()) throw RuntimeException("Params can not br empty")
    var maxNum = nums[0]
    for(num in nums){
        if(num > maxNum){
            maxNum = num
        }
    }
    return maxNum
}

fun main(){
    val a = 1.423143
    val b = 23.4
    val c = 1.3213
    val largest = max(a,b,c)
    println("-------" + largest + "------")
}