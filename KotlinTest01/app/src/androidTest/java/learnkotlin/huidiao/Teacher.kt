package learnkotlin.huidiao

class Teacher(student: Student) : Callback {

    var student: Student = student

    fun askQuestion(){
        student.resolveQuestion(this)
    }


    override fun tellAnswer(answer: Int) {
        println("老师说,我知道了,你的答案是" + answer)
    }
}