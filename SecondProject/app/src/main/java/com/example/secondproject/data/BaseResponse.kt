package com.example.secondproject.data

import java.lang.Exception

data class BaseResponse<T>(
    var data :T,
    var errorCode: Int,
    var errorMsg: String
)
//errorCode = 0 代表执行成功，不建议依赖任何非0的 errorCode.
//errorCode = -1001 代表登录失效，需要重新登录

