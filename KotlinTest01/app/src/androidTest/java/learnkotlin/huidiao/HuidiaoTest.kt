package learnkotlin.huidiao

fun main(){
    val student01 : Student = Lihua()
    val student02 : Student = Zhangsan()
    val teacher = Teacher(student01)
    teacher.askQuestion()
}