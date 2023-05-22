package com.example.kotlinxiechenglearn01

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CoroutinesTest {

}
fun main(){
    GlobalScope.launch {
        println("codds run in coroutine scope")
    }
    Thread.sleep(1000)
}