package learnkotlin

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    test()
    println("end")
}

fun test(){
    val start = System.currentTimeMillis()
    runBlocking {
        repeat(15000000){
            launch {
                while (true){

                    println(".")
                }
            }
        }
    }
    val end = System.currentTimeMillis()
    println(end - start)
}
