package com.example.retrofitlearntest01

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface GetRequest_Interface {
    @GET("ajax.php?a=fy&f=auto&t=auto&w=hello%20world")
    fun getCall() : Call<Translation>
}