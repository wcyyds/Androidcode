
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSource
import okio.ForwardingSource
import okio.Source
import okio.buffer

interface ProgressListener{
    fun updata(bytesRead: Long, contentLength: Long, done: Boolean)
}

fun main(){
    run()
}

fun run(){
    //封装请求报文的消息
    val request = Request.Builder()
        .url("https://images.pexels.com/photos/5177790/pexels-photo-5177790.jpeg")
        .build()

    //
    val progressListener :ProgressListener = object : ProgressListener{

        var fistUpdater = true

        override fun updata(bytesRead: Long, contentLength: Long, done: Boolean) {
            if(done){
                println("传输完成")
            }else{
                if (fistUpdater){
                    fistUpdater = false
                    if(contentLength == -1L){
                        println("不知道响应的数据长度")
                    }else{
                        println("获取的的响应数据的长度 " + contentLength)
                    }
                }

                println("现在字节数" + bytesRead + ".总字节数" + contentLength)

                if(contentLength != -1L){
                    println("进度" + (100*bytesRead)/contentLength + "%")
                }

            }

        }

    }

    //OK的http请求的客户端类,配置超时时间,缓存目录,拦截器等
    //这里只是添加了一个自定义的网络拦截器
    val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(ProgressInterceptor(progressListener))
        .build()

    //封装了响应数据的报文信息
    val response = okHttpClient.newCall(request).execute()

    println(response.body?.string())

}

//自定义拦截器,我们把它放到网络拦截器上面,因为是和网络请求相关的
class ProgressInterceptor constructor(private val progressListener: ProgressListener) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //自定义拦截器三步走
        val requestIntercept = chain.request()
        val response = chain.proceed(requestIntercept)
        //这里对获取的response进行封装然后返回一个新的response

        //创建一个新的Response.Builder对象，并将响应的主体（body）替换为一个新的ProgressResponseBody对象来封装响应。
        // ProgressResponseBody类是一个自定义的ResponseBody实现类，用于监控响应体的读取进度。
        val builder = response.newBuilder()
            .body(response.body?.let {  ProgressResponseBody(it, progressListener) })
        return builder.build()
    }
}

//对原始的responsebody进行封装,返回封装后的responsebody
class ProgressResponseBody constructor
    (private val responseBody: ResponseBody,
     private val progressListener: ProgressListener) : ResponseBody() {

    private var bufferedSource: BufferedSource? = null

    //响应数据的长度
    override fun contentLength(): Long {
        return responseBody.contentLength()
    }

    //响应数据的内容
    override fun contentType(): MediaType? {
        return responseBody.contentType()
    }

    //返回响应数据的来源bufferedSource
    override fun source(): BufferedSource {
        if(bufferedSource == null){
            bufferedSource = lister(responseBody.source()).buffer()
        }
        return bufferedSource!!
    }

    //创建一个自定义的ForwardingSource对象，用于监控从响应体源中读取的数据。
    private fun lister(source: Source): Source{
        return object :ForwardingSource(source){
            var totalBytesRead: Long = 0L;

            override fun read(sink: Buffer, byteCount: Long): Long {
                //调用了父类的 read() 方法来读取数据，并将返回值（即实际读取的字节数）保存到 bytesRead 变量中。
                // 然后，通过判断 bytesRead 是否等于 -1，来判断是否已经读取完了所有的数据。
                // 如果 bytesRead 等于 -1，说明已经读取完了所有数据，此时将 bytesRead 设置为 0，
                // 否则就将 bytesRead 的值累加到 totalBytesRead 变量中。
                val bytesRead = super.read(sink, byteCount)
                totalBytesRead += if(bytesRead != -1L) bytesRead else 0
                //将已经读取的字节数、响应数据的总长度以及是否已经读取完毕的状态信息传递给监听器
                progressListener.updata(totalBytesRead, responseBody.contentLength(),
                    bytesRead == -1L)
                return bytesRead
            }
        }

    }

}