package learnkotlin

import java.lang.Exception


//密封类的使用
sealed class Result
class Success(val msg: String) : Result()
class Failure(val error: Exception) : Result()

fun getResultMsg(result: Result) = when(result){
    is Success -> result.msg
    is Failure -> result.error.message
}