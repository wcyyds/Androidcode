package learnkotlin

open class Base(){
    open fun fun1(){

    }
    fun fun2(){

    }
}

class SubClass() : Base(){
     override fun fun1() {

        super.fun1()
    }
}