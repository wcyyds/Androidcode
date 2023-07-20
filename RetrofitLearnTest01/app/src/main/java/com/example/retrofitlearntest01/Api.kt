package com.example.retrofitlearntest01

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
        // post请求，如果有参数需要添加 @FormUrlEncoded注解，即和@Field配合使用
        @FormUrlEncoded
        @POST("api/comments.163")
        fun postDataCall(@Field("format") format: String): Call<Any>
}
