
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
    val request = Request.Builder()
        .url("https://images.pexels.com/photos/5177790/pexels-photo-5177790.jpeg")
        .build()

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

    val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor{chain :Interceptor.Chain->
        val originalResponse = chain.proceed(chain.request())
        originalResponse.newBuilder()
            .body(ProgressResponseBody(originalResponse.body!!, progressListener))
            .build()
        }
        .build()

    val response = okHttpClient.newCall(request).execute()

    println(response.body?.string())

}

class ProgressInterceptor constructor(private val progressListener: ProgressListener) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        //自定义拦截器三步走
        val requestIntercept = chain.request()
        val response = chain.proceed(requestIntercept)
        //这里对获取的response进行封装然后返回一个新的response

        //这里有些问题
        val builder = response.newBuilder()
            .body(response.body?.let {  ProgressResponseBody(it, progressListener) })
        return builder.build()
    }
}

class ProgressResponseBody constructor
    (private val responseBody: ResponseBody,
     private val progressListener: ProgressListener) : ResponseBody() {

    private var bufferedSource: BufferedSource? = null

    override fun contentLength(): Long {
        return responseBody.contentLength()
    }

    override fun contentType(): MediaType? {
        return responseBody.contentType()
    }

    override fun source(): BufferedSource {
        if(bufferedSource == null){
            bufferedSource = source(responseBody.source()).buffer()
        }
        return bufferedSource!!
    }

    private fun source(source: Source): Source{
        return object :ForwardingSource(source){
            var totalBytesRead: Long = 0L;

            override fun read(sink: Buffer, byteCount: Long): Long {
                val bytesRead = super.read(sink, byteCount)
                totalBytesRead += if(bytesRead != -1L) bytesRead else 0
                progressListener.updata(totalBytesRead, responseBody.contentLength(),
                    bytesRead == -1L)
                return bytesRead
            }
        }

    }

}