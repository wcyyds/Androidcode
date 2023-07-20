package com.example.retrofitlearntest01

import retrofit2.Call
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface PostRequest_Interface {
    @POST("index.php?c=trans&m=fy&client=6&auth_user=key_web_fanyi&sign=4b5dcb6d774603bc")
    @FormUrlEncoded
    fun getCall(@FieldMap map: MutableMap<String, Any>): Call<Translation1>
}