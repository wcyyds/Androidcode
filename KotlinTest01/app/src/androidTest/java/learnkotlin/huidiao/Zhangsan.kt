package learnkotlin.huidiao

class Zhangsan : Student {
    override fun resolveQuestion(callback: Callback) {
        callback.tellAnswer(1)
    }
}