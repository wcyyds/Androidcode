package com.example.retrofitlearntest01

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class Kotlin_Coroutine {
}

//项目中应用协程
fun now() {
    val job = Job()
    //有了CoroutineScope对象,我们就可以随意的创建一个协程了
    val scope = CoroutineScope(job)
    scope.launch {
        //处理具体的逻辑
        println("wang01")
    }
    scope.launch {
        //处理具体的逻辑2
        println("wang02")
    }
    //只需要调用一次cancel就可以将同一作用域下面的所有协程全部取消
    job.cancel()
}

fun main() {
    runBlocking {
        val start = System.currentTimeMillis()
        val deferred1 = async {
            delay(1000)
            5 + 5
        }
        val deferred2 = async {
            delay(1000)
            4 + 6
        }
        println("result is ${deferred1.await() + deferred2.await()}.")
        val end = System.currentTimeMillis()
        println("cost ${end - start} milliseconds.")
    }

    testwithcontext()
}

fun testwithcontext(){
    runBlocking {
        val result = withContext(Dispatchers.Default){
            5 + 6
        }
        println(result)
    }
}