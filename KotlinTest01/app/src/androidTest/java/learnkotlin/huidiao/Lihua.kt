package learnkotlin.huidiao

class Lihua : Student {
    override fun resolveQuestion(callback: Callback) {
        Thread.sleep(1000)

        callback.tellAnswer(8)
    }
}