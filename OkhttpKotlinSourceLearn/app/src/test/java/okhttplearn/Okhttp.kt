package okhttplearn

import okhttp3.OkHttpClient
import okhttp3.Request
import java.time.Duration

const val URL = "https://publicobject.com/helloworld.txt"


//OKhttp的同步请求
fun main(){
    val okhttpCliet = OkHttpClient.Builder()
        .connectTimeout(Duration.ofSeconds(10))
        .readTimeout(Duration.ofSeconds(10))
        .writeTimeout(Duration.ofSeconds(10))
        .retryOnConnectionFailure(true)
        .build()
    val request = Request.Builder().url(URL).build()
    val call = okhttpCliet.newCall(request)
    /*
    我们调用execute方法发送同步请求时,主要逻辑
    1.判断是否有重复的请求
    2.事件的记录档案
    3.将自身加到dispatcher(调度器)当中去
        然后在结束后请求后在dispatcher中移除自身
    4.最后通过getReasponseWithInterceptorChain方法得到Response对象
     */
    /*
    execute源码分析
    override fun execute(): Response {
    check(executed.compareAndSet(false, true)) { "Already Executed" }
    timeout.enter()
    callStart()
    try {
      client.dispatcher.executed(this)
      return getResponseWithInterceptorChain()
    } finally {
      client.dispatcher.finished(this)
    }
  }
  check(executed.compareAndSet(false, true))：这是一个线程安全的检查，如果已经执行过该请求，那么会抛出一个异常 "Already Executed"。如果该请求还没有被执行过，那么它会将 executed 标志设置为 true，以确保该请求只会被执行一次。

timeout.enter()：这是一个计时器，用于检测请求是否超时。在该函数被执行之前，该计时器已经被设置好了。

callStart()：这是一个回调函数，用于通知客户端该请求已经开始执行。

client.dispatcher.executed(this)：这是一个回调函数，用于通知客户端该请求已经被执行。

getResponseWithInterceptorChain()：该函数是一个拦截器链，用于处理请求和响应。该函数将会依次执行所有的拦截器，直至得到最终的响应。

client.dispatcher.finished(this)：这是一个回调函数，用于通知客户端该请求已经执行完毕。

总的来说，该函数的作用是执行网络请求，并通过拦截器链处理请求和响应。在执行请求之前，它会进行一些检查和初始化操作，执行请求后，它会将请求的执行状态和结果通知给客户端。
     */
    val response = call.execute()
    println(response.body?.string())
}